package Ampersand.GKR.domain.equipment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListEquipmentResponse {

    private List<EquipmentResponse> equipmentList;
}
