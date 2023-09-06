package Ampersand.GKR.domain.violation.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ListViolationInfoResponse {

    List<ViolationInfoResponse> violationList;
}
