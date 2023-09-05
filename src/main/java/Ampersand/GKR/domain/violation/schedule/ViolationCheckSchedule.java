package Ampersand.GKR.domain.violation.schedule;

import Ampersand.GKR.domain.violation.service.ViolationCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViolationCheckSchedule {

    private final ViolationCheckService violationCheckService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void violationCheck() {
        violationCheckService.execute();
    }
}
