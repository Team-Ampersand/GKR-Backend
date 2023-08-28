package Ampersand.GKR.domain.order.presentation.dto.response;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ApplicationResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private OrderType orderType;

    private String userName;

    private String reason;

    private LocalDateTime rentalStartDate;

    private LocalDateTime rentalEndDate;

    public static ApplicationResponse toResponse(Application application) {

        return ApplicationResponse.builder()
                .id(application.getEquipment().getId())
                .name(application.getEquipment().getName())
                .imageUrl(application.getEquipment().getImageUrl())
                .description(application.getEquipment().getDescription())
                .orderType(application.getOrderType())
                .userName(application.getUserName())
                .reason(application.getReason())
                .rentalStartDate(application.getRentalStartDate())
                .rentalEndDate(application.getRentalEndDate())
                .build();
    }
}
