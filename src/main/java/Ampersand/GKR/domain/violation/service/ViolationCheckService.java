package Ampersand.GKR.domain.violation.service;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.violation.entity.Violation;
import Ampersand.GKR.domain.violation.repository.ViolationRepository;
import Ampersand.GKR.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RollbackService
@RequiredArgsConstructor
public class ViolationCheckService {

    private final ViolationRepository violationRepository;

    public void execute() {

        List<Violation> violations = violationRepository.findAllByMostRecentViolation();

        for (Violation violation : violations) {
            if (violation != null && violation.getViolationEndDate().isBefore(LocalDateTime.now())) {

                User user = violation.getUser();

                user.setRentalRestricted(false);
            }
        }
    }
}
