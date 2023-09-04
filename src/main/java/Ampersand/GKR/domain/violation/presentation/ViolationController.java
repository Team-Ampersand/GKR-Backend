package Ampersand.GKR.domain.violation.presentation;

import Ampersand.GKR.domain.violation.presentation.dto.request.ViolationRequest;
import Ampersand.GKR.domain.violation.service.ViolationService;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestRequestService("/violation")
public class ViolationController {

    private final ViolationService violationService;

    @PostMapping
    public ResponseEntity<Void> violation(@RequestBody ViolationRequest violationRequest) {
        violationService.execute(violationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
