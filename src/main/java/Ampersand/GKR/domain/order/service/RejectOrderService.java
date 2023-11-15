package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.enums.OrderType;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.OrderUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class RejectOrderService {

    private final OrderUtil orderUtil;

    public void execute(Long id) {

        Application application = orderUtil.findApplicationById(id);

        application.setOrderStatus(OrderStatus.REJECT);

        if (application.getOrderType() == OrderType.RENTAL) {
            application.getEquipment().setEquipmentStatus(EquipmentStatus.NOT_RENT);
        } else application.getEquipment().setEquipmentStatus(EquipmentStatus.RENTING);
    }
}
