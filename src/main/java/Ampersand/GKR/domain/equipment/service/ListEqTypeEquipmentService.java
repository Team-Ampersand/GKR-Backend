package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import Ampersand.GKR.domain.equipment.presentation.dto.response.ListEquipmentResponse;
import Ampersand.GKR.domain.equipment.repository.EquipmentRepository;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.equipment.presentation.dto.response.EquipmentResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListEqTypeEquipmentService {

    private final EquipmentRepository equipmentRepository;

    public ListEquipmentResponse execute(EquipmentType equipmentType) {

        List<Equipment> equipments = equipmentRepository.findByEquipmentType(equipmentType);

        ListEquipmentResponse listEquipmentResponse = ListEquipmentResponse.builder()
                .equipmentList(
                        equipments.stream()
                                .map(equipment -> toResponse(equipment))
                                .collect(Collectors.toList())
                )
                .build();

        return listEquipmentResponse;
    }
}
