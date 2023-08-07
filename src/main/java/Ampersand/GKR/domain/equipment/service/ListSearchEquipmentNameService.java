package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.presentation.dto.request.SearchEquipmentNameRequest;
import Ampersand.GKR.domain.equipment.presentation.dto.response.ListEquipmentResponse;
import Ampersand.GKR.domain.equipment.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.equipment.presentation.dto.response.EquipmentResponse.toResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ListSearchEquipmentNameService {

    private final EquipmentRepository equipmentRepository;

    public ListEquipmentResponse execute(SearchEquipmentNameRequest searchEquipmentNameRequest) {

        List<Equipment> names = equipmentRepository.findByName(searchEquipmentNameRequest.getName());

        ListEquipmentResponse listSearchBoardResponse = ListEquipmentResponse.builder()
                .equipmentList(
                        names.stream()
                                .map(equipment -> toResponse(equipment))
                                .collect(Collectors.toList())
                )
                .build();

        return listSearchBoardResponse;
    }
}
