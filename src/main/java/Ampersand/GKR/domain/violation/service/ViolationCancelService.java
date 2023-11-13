package Ampersand.GKR.domain.violation.service;

import Ampersand.GKR.domain.violation.entity.Violation;
import Ampersand.GKR.domain.violation.exception.NotCurrentlyViolatingException;
import Ampersand.GKR.domain.violation.exception.ViolationNotFoundException;
import Ampersand.GKR.domain.violation.repository.ViolationRepository;
import Ampersand.GKR.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class ViolationCancelService {

    private final ViolationRepository violationRepository;

    public void execute(Long id) {

        Violation violation = violationRepository.findById(id)
                .orElseThrow(() -> new ViolationNotFoundException());

        if (violation.getUser().isRentalRestricted() == false && violation.isCanceled() == false) {
            throw new NotCurrentlyViolatingException();
        }

        violation.setCanceled(false);

        violation.getUser().setRentalRestricted(false);
    }
}
