package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.RentStatus;
import Ampersand.GKR.domain.equipment.presentation.dto.response.ListEquipmentResponse;
import Ampersand.GKR.domain.equipment.repository.EquipmentRepository;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.equipment.presentation.dto.response.EquipmentResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListRentStatusEquipmentService {

    private final EquipmentRepository equipmentRepository;

    public ListEquipmentResponse execute(RentStatus rentStatus) {

        List<Equipment> equipments = equipmentRepository.findByRentStatus(rentStatus);

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
