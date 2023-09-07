package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.enums.OrderType;
import Ampersand.GKR.domain.order.exception.EquipmentAlreadyReturnStatusException;
import Ampersand.GKR.domain.order.exception.NotMyRentedEquipmentException;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.OrderUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class ReturnEquipmentService {

    private final OrderUtil orderUtil;

    private final UserUtil userUtil;

    public void execute(Long id) {

        User user = userUtil.currentUser();

        Application application = orderUtil.findApplicationById(id);

        if (!application.getUser().equals(user)) {
            throw new NotMyRentedEquipmentException();
        }

        if (application.getOrderType().equals(OrderType.RETURN)) {
            throw new EquipmentAlreadyReturnStatusException();
        }

        application.setOrderInfo(OrderStatus.WAITING, OrderType.RETURN);
    }

}
