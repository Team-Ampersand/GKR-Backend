package Ampersand.GKR.domain.violation.presentation.dto.response;

import Ampersand.GKR.domain.violation.entity.Violation;
import Ampersand.GKR.domain.violation.enums.ViolationReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ViolationInfoResponse {

    private ViolationReason violationReason;

    private LocalDateTime violationStartDate;

    private LocalDateTime violationEndDate;

    public static ViolationInfoResponse toResponse(Violation violation) {

        return ViolationInfoResponse.builder()
                .violationReason(violation.getViolationReason())
                .violationStartDate(violation.getViolationStartDate())
                .violationEndDate(violation.getViolationEndDate())
                .build();
    }
}
