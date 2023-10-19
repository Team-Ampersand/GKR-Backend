package Ampersand.GKR.domain.violation.service;

import Ampersand.GKR.domain.violation.entity.Violation;
import Ampersand.GKR.domain.violation.presentation.dto.response.ListViolationInfoResponse;
import Ampersand.GKR.domain.violation.repository.ViolationRepository;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.violation.presentation.dto.response.ViolationInfoResponse.toAllResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListAllViolationService {

    private final ViolationRepository violationRepository;

    public ListViolationInfoResponse execute() {

        List<Violation> violations = violationRepository.findAllCurrentViolations();

        ListViolationInfoResponse listViolationInfoResponse = ListViolationInfoResponse.builder()
                .violationList(
                        violations.stream()
                                .map(violation -> toAllResponse(violation))
                                .collect(Collectors.toList())
                )
                .build();

        return listViolationInfoResponse;
    }
}
