package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.enums.OrderType;
import Ampersand.GKR.domain.order.exception.EquipmentNotRentedException;
import Ampersand.GKR.domain.order.exception.NotMyRentedEquipmentException;
import Ampersand.GKR.domain.order.presentation.dto.request.ExtensionEquipmentRequest;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.OrderUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class ExtensionEquipmentService {

    private final OrderUtil orderUtil;

    private final UserUtil userUtil;

    public void execute(Long id, ExtensionEquipmentRequest extensionEquipmentRequest) {

        User user = userUtil.currentUser();

        Application application = orderUtil.findApplicationById(id);

        if (!application.getOrderStatus().equals(OrderStatus.ACCEPT)) {
            throw new EquipmentNotRentedException();
        }

        if (!application.getUser().equals(user)) {
            throw new NotMyRentedEquipmentException();
        }

        application.extensionReason(extensionEquipmentRequest.getReason());

        application.setOrderInfo(OrderStatus.WAITING, OrderType.EXTENSION);
    }
}
