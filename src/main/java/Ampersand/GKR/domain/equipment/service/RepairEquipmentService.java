package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.order.exception.ExistsRequestException;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.EquipmentUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class RepairEquipmentService {

    private final EquipmentUtil equipmentUtil;

    public void execute(Long id) {

        Equipment equipment = equipmentUtil.findEquipmentById(id);

        if (!equipment.getEquipmentStatus().equals(EquipmentStatus.NOT_RENT)) {
            throw new ExistsRequestException();
        }

        equipment.setEquipmentStatus(EquipmentStatus.REPAIRING);
    }
}
