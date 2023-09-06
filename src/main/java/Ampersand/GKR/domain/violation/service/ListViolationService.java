package Ampersand.GKR.domain.violation.service;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.violation.entity.Violation;
import Ampersand.GKR.domain.violation.presentation.dto.response.ListViolationInfoResponse;
import Ampersand.GKR.domain.violation.repository.ViolationRepository;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.violation.presentation.dto.response.ViolationInfoResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListViolationService {

    private final ViolationRepository violationRepository;

    private final UserUtil userUtil;

    public ListViolationInfoResponse execute() {

        User user = userUtil.currentUser();

        List<Violation> violations = violationRepository.findByUser(user);

        ListViolationInfoResponse listViolationInfoResponse = ListViolationInfoResponse.builder()
                .violationList(
                        violations.stream()
                                .map(violation -> toResponse(violation))
                                .collect(Collectors.toList())
                )
                .build();

        return listViolationInfoResponse;
    }
}