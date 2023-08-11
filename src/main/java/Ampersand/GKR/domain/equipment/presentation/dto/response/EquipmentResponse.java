package Ampersand.GKR.domain.equipment.presentation.dto.response;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EquipmentResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private EquipmentStatus equipmentStatus;

    private EquipmentType equipmentType;

    public static EquipmentResponse toResponse(Equipment equipment) {

        return EquipmentResponse.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .imageUrl(equipment.getImageUrl())
                .description(equipment.getDescription())
                .equipmentStatus(equipment.getEquipmentStatus())
                .equipmentType(equipment.getEquipmentType())
                .build();
    }
}
