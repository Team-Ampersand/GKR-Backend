package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.EquipmentUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class RepairCompletionEquipmentService {

    private final EquipmentUtil equipmentUtil;

    public void execute(Long id) {

        Equipment equipment = equipmentUtil.findEquipmentById(id);

        equipment.setEquipmentStatus(EquipmentStatus.NOT_RENT);
    }
}