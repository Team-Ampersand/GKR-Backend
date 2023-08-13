package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.enums.OrderType;
import Ampersand.GKR.domain.order.exception.EquipmentAlreadyRentalException;
import Ampersand.GKR.domain.order.presentation.dto.request.RentalEquipmentRequest;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.EquipmentUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class RentalEquipmentService {

    private final ApplicationRepository applicationRepository;

    private final EquipmentUtil equipmentUtil;

    private final UserUtil userUtil;

    public void execute(Long id, RentalEquipmentRequest rentalEquipmentRequest) {

        User user = userUtil.currentUser();

        Equipment equipment = equipmentUtil.findEquipmentById(id);

        if (!equipment.getEquipmentStatus().equals(EquipmentStatus.NOT_RENT)) {
            throw new EquipmentAlreadyRentalException();
        }

        Application application = Application.builder()
                .rentalDate(rentalEquipmentRequest.getRentalDate())
                .reason(rentalEquipmentRequest.getReason())
                .orderStatus(OrderStatus.WAITING)
                .orderType(OrderType.RENTAL)
                .equipment(equipment)
                .userName(user.getName())
                .user(user)
                .build();

        applicationRepository.save(application);

        equipment.setEquipmentStatus(EquipmentStatus.WAITING);
    }
}
