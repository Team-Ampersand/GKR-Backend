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

    private String email;

    private int grade;

    private int classNum;

    private int stuNum;

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
                .email(application.getUser().getEmail())
                .grade(application.getUser().getGrade())
                .classNum(application.getUser().getClassNum())
                .stuNum(application.getUser().getStuNum())
                .reason(application.getReason())
                .rentalStartDate(application.getRentalStartDate())
                .rentalEndDate(application.getRentalEndDate())
                .build();
    }
}
