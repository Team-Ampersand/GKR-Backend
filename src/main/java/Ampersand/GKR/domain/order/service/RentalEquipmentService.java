package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.RentalStatus;
import Ampersand.GKR.domain.order.enums.RentalType;
import Ampersand.GKR.domain.order.exception.EquipmentAlreadyRentalException;
import Ampersand.GKR.domain.order.presentation.dto.request.RentalEquipmentRequest;
import Ampersand.GKR.domain.order.repository.RentalRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.EquipmentUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class RentalEquipmentService {

    private final RentalRepository rentalRepository;

    private final EquipmentUtil equipmentUtil;

    private final UserUtil userUtil;

    public void execute(Long id, RentalEquipmentRequest rentalEquipmentRequest) {

        User user = userUtil.currentUser();

        Equipment equipment = equipmentUtil.findEquipmentById(id);

        if (equipment.getEquipmentStatus().equals(EquipmentStatus.RENTING)) {
            throw new EquipmentAlreadyRentalException();
        }

        Application application = Application.builder()
                .rentalDate(rentalEquipmentRequest.getRentalDate())
                .reason(rentalEquipmentRequest.getReason())
                .rentalStatus(RentalStatus.WAITING)
                .rentalType(RentalType.RENTAL)
                .equipment(equipment)
                .userName(user.getName())
                .user(user)
                .build();

        rentalRepository.save(application);

        equipment.setEquipmentStatus(EquipmentStatus.WAITING);
    }
}
