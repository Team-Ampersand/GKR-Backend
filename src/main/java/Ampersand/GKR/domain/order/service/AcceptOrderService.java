package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.OrderUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class AcceptOrderService {

    private final OrderUtil orderUtil;

    private final UserUtil userUtil;

    private final ApplicationRepository applicationRepository;

    public void execute(Long id) {

        User user = userUtil.currentUser();

        Application application = orderUtil.findApplicationById(id);

        application.setOrderStatus(OrderStatus.ACCEPT);

        switch (application.getOrderType()) {
            case RENTAL :
                application.getEquipment().setEquipmentStatus(EquipmentStatus.RENTING);
                application.setRentalDates();
                return;
            case RETURN:
                application.getEquipment().setEquipmentStatus(EquipmentStatus.NOT_RENT);
                applicationRepository.delete(application);
            case EXTENSION:
                application.extensionDate();
        }
    }
}
