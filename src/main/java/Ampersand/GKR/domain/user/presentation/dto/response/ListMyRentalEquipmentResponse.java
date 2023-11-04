package Ampersand.GKR.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ListMyRentalEquipmentResponse {

    private List<MyRentalEquipmentResponse> rentalList;
}
