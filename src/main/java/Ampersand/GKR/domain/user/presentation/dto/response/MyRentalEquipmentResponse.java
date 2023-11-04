package Ampersand.GKR.domain.user.presentation.dto.response;

import Ampersand.GKR.domain.order.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyRentalEquipmentResponse {

    private Long id;

    public static MyRentalEquipmentResponse toResponse(Application application) {

        return MyRentalEquipmentResponse.builder()
                .id(application.getEquipment().getId())
                .build();
    }
}
