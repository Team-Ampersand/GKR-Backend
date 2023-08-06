package Ampersand.GKR.domain.equipment.presentation.dto.response;

import Ampersand.GKR.domain.equipment.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DetailEquipmentResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private RentStatus rentStatus;
}
