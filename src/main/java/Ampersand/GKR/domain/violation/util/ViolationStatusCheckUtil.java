package Ampersand.GKR.domain.violation.util;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.violation.exception.CurrentlyViolatingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViolationStatusCheckUtil {
    public void violationCheck(User user) {
        if (user.isRentalRestricted()) {
            throw new CurrentlyViolatingException();
        }
    }
}
