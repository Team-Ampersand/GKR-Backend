package Ampersand.GKR.domain.violation.presentation;

import Ampersand.GKR.domain.violation.presentation.dto.request.ViolationRequest;
import Ampersand.GKR.domain.violation.presentation.dto.response.ListViolationInfoResponse;
import Ampersand.GKR.domain.violation.service.ListAllViolationService;
import Ampersand.GKR.domain.violation.service.ListViolationService;
import Ampersand.GKR.domain.violation.service.ViolationCancelService;
import Ampersand.GKR.domain.violation.service.ViolationService;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestRequestService("/violation")
public class ViolationController {

    private final ViolationService violationService;

    private final ViolationCancelService violationCancelService;

    private final ListViolationService listViolationService;

    private final ListAllViolationService listAllViolationService;

    @PostMapping
    public ResponseEntity<Void> violation(@RequestBody ViolationRequest violationRequest) {
        violationService.execute(violationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> violationCancel(@PathVariable Long id) {
        violationCancelService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListViolationInfoResponse> violationInfo() {
        var list = listViolationService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ListViolationInfoResponse> violationAllInfo() {
        var list = listAllViolationService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
