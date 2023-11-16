package Ampersand.GKR.domain.equipment.presentation.dto.response;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import Ampersand.GKR.domain.order.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EquipmentResponse {

    private Long applicationId;

    private Long equipmentId;

    private String name;

    private String imageUrl;

    private String description;

    private EquipmentStatus equipmentStatus;

    private EquipmentType equipmentType;

    public static EquipmentResponse toResponse(Equipment equipment) {

        return EquipmentResponse.builder()
                .equipmentId(equipment.getId())
                .name(equipment.getName())
                .imageUrl(equipment.getImageUrl())
                .description(equipment.getDescription())
                .equipmentStatus(equipment.getEquipmentStatus())
                .equipmentType(equipment.getEquipmentType())
                .build();
    }

    public static EquipmentResponse toResponse(Application application) {

        return EquipmentResponse.builder()
                .applicationId(application.getId())
                .equipmentId(application.getEquipment().getId())
                .name(application.getEquipment().getName())
                .imageUrl(application.getEquipment().getImageUrl())
                .description(application.getEquipment().getDescription())
                .equipmentStatus(application.getEquipment().getEquipmentStatus())
                .equipmentType(application.getEquipment().getEquipmentType())
                .build();
    }
}
