package Ampersand.GKR.domain.violation.presentation.dto.response;

import Ampersand.GKR.domain.violation.entity.Violation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ViolationInfoResponse {

    private String userName;

    private String violationReason;

    private LocalDateTime violationStartDate;

    private LocalDateTime violationEndDate;

    public static ViolationInfoResponse toResponse(Violation violation) {

        return ViolationInfoResponse.builder()
                .violationReason(violation.getViolationReason())
                .violationStartDate(violation.getViolationStartDate())
                .violationEndDate(violation.getViolationEndDate())
                .build();
    }

    public static ViolationInfoResponse toAllResponse(Violation violation) {

        return ViolationInfoResponse.builder()
                .userName(violation.getUser().getName())
                .violationReason(violation.getViolationReason())
                .violationStartDate(violation.getViolationStartDate())
                .violationEndDate(violation.getViolationEndDate())
                .build();
    }
}
