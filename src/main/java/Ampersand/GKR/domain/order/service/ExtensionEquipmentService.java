package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.enums.OrderType;
import Ampersand.GKR.domain.order.exception.NotMyRentedEquipmentException;
import Ampersand.GKR.domain.order.presentation.dto.request.ExtensionEquipmentRequest;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.violation.util.ViolationStatusCheckUtil;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.OrderUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class ExtensionEquipmentService {

    private final OrderUtil orderUtil;

    private final UserUtil userUtil;

    private final ViolationStatusCheckUtil violationStatusCheckUtil;

    public void execute(Long id, ExtensionEquipmentRequest extensionEquipmentRequest) {

        User user = userUtil.currentUser();

        violationStatusCheckUtil.violationCheck(user);

        Application application = orderUtil.findApplicationById(id);

        if (!application.getUser().equals(user)) {
            throw new NotMyRentedEquipmentException();
        }

        application.extensionReason(extensionEquipmentRequest.getReason());

        application.setOrderInfo(OrderStatus.WAITING, OrderType.EXTENSION);
    }
}
