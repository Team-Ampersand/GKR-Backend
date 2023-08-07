package Ampersand.GKR.domain.equipment.presentation.dto.request;

import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateEquipmentRequest {

    private String name;

    private String description;

    private EquipmentType equipmentType;
}
