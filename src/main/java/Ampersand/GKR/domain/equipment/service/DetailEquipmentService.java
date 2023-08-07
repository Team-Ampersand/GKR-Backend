package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.presentation.dto.response.DetailEquipmentResponse;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.EquipmentUtil;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class DetailEquipmentService {

    private final EquipmentUtil equipmentUtil;

    public DetailEquipmentResponse execute(Long id) {

        Equipment equipment = equipmentUtil.findEquipmentById(id);

        DetailEquipmentResponse detailEquipmentResponse = DetailEquipmentResponse.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .imageUrl(equipment.getImageUrl())
                .description(equipment.getDescription())
                .rentStatus(equipment.getRentStatus())
                .equipmentType(equipment.getEquipmentType())
                .build();

        return detailEquipmentResponse;
    }
}
