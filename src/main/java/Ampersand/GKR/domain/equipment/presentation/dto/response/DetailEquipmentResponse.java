package Ampersand.GKR.domain.equipment.presentation.dto.response;

import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DetailEquipmentResponse {

    private Long equipmentId;

    private Long applicationId;

    private String name;

    private String imageUrl;

    private String description;

    private EquipmentStatus equipmentStatus;

    private EquipmentType equipmentType;
}
