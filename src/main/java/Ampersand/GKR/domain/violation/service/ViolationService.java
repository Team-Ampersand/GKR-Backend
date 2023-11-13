package Ampersand.GKR.domain.violation.service;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.exception.UserNotFoundException;
import Ampersand.GKR.domain.user.repository.UserRepository;
import Ampersand.GKR.domain.violation.entity.Violation;
import Ampersand.GKR.domain.violation.exception.AlreadyViolatingException;
import Ampersand.GKR.domain.violation.presentation.dto.request.ViolationRequest;
import Ampersand.GKR.domain.violation.repository.ViolationRepository;
import Ampersand.GKR.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class ViolationService {

    private final UserRepository userRepository;

    private final ViolationRepository violationRepository;

    public void execute(ViolationRequest violationRequest) {

        User user = userRepository.findByEmail(violationRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException());

        if (user.isRentalRestricted() == true) {
            throw new AlreadyViolatingException();
        }

        Violation violation = Violation.builder()
                .violationReason(violationRequest.getViolationReason())
                .user(user)
                .canceled(true)
                .build();

        violationRepository.save(violation);

        violation.setViolationDates();

        user.setRentalRestricted(true);
    }
}
