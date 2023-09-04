package Ampersand.GKR.domain.violation.presentation.dto.request;

import Ampersand.GKR.domain.violation.enums.ViolationReason;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViolationRequest {

    private String email;

    private ViolationReason violationReason;
}
