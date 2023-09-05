package Ampersand.GKR.domain.violation.service;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.exception.UserNotFoundException;
import Ampersand.GKR.domain.user.repository.UserRepository;
import Ampersand.GKR.domain.violation.exception.NotCurrentlyViolatingException;
import Ampersand.GKR.domain.violation.presentation.dto.request.ViolationCancelRequest;
import Ampersand.GKR.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class ViolationCancelService {

    private final UserRepository userRepository;

    public void execute(ViolationCancelRequest violationCancelRequest) {

        User user = userRepository.findByEmail(violationCancelRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException());

        if (user.isRentalRestricted() == false) {
            throw new NotCurrentlyViolatingException();
        }

        user.setRentalRestricted(false);
    }
}
