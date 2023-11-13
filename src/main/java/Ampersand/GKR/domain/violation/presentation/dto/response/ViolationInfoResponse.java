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

    private Long id;

    private String userName;

    private String violationReason;

    private LocalDateTime violationStartDate;

    private LocalDateTime violationEndDate;

    private boolean canceled;

    public static ViolationInfoResponse toResponse(Violation violation) {

        return ViolationInfoResponse.builder()
                .id(violation.getId())
                .violationReason(violation.getViolationReason())
                .violationStartDate(violation.getViolationStartDate())
                .violationEndDate(violation.getViolationEndDate())
                .canceled(violation.isCanceled())
                .build();
    }

    public static ViolationInfoResponse toAllResponse(Violation violation) {

        return ViolationInfoResponse.builder()
                .id(violation.getId())
                .userName(violation.getUser().getName())
                .violationReason(violation.getViolationReason())
                .violationStartDate(violation.getViolationStartDate())
                .violationEndDate(violation.getViolationEndDate())
                .canceled(violation.isCanceled())
                .build();
    }
}
