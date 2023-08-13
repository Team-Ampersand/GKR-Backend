package Ampersand.GKR.domain.order.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ListOrderEquipmentResponse {

    private List<OrderEquipmentResponse> orderEquipmentList;
}
