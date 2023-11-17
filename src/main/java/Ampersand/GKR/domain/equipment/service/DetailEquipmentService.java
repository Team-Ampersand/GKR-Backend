package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.presentation.dto.response.DetailEquipmentResponse;
import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.EquipmentUtil;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class DetailEquipmentService {

    private final EquipmentUtil equipmentUtil;

    private final ApplicationRepository applicationRepository;

    public DetailEquipmentResponse execute(Long id) {

        Equipment equipment = equipmentUtil.findEquipmentById(id);

        Application application = applicationRepository.findByEquipment(equipment);

        Long applicationId = (application != null) ? application.getId() : null;

        DetailEquipmentResponse detailEquipmentResponse = DetailEquipmentResponse.builder()
                .equipmentId(equipment.getId())
                .applicationId(applicationId)
                .name(equipment.getName())
                .imageUrl(equipment.getImageUrl())
                .description(equipment.getDescription())
                .equipmentStatus(equipment.getEquipmentStatus())
                .equipmentType(equipment.getEquipmentType())
                .build();

        return detailEquipmentResponse;
    }
}
