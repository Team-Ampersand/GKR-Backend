package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.enums.OrderType;
import Ampersand.GKR.domain.order.exception.NotMyRentedEquipmentException;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.OrderUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class OrderCancelService {

    private final UserUtil userUtil;

    private final OrderUtil orderUtil;

    private final ApplicationRepository applicationRepository;

    public void execute(Long id) {

        User user = userUtil.currentUser();

        Application application = orderUtil.findApplicationById(id);

        if (!application.getUser().equals(user)) {
            throw new NotMyRentedEquipmentException();
        }

        switch (application.getOrderType()) {
            case RENTAL:
                application.getEquipment().setEquipmentStatus(EquipmentStatus.NOT_RENT);
                applicationRepository.delete(application);
            case EXTENSION:
                application.setOrderInfo(OrderStatus.ACCEPT, OrderType.RENTAL);
            case RETURN:
                application.setOrderInfo(OrderStatus.ACCEPT, OrderType.RENTAL);
        }
    }
}
