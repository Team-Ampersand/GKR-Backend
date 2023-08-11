package Ampersand.GKR.domain.order.presentation;

import Ampersand.GKR.domain.order.presentation.dto.request.RentalEquipmentRequest;
import Ampersand.GKR.domain.order.service.RentalEquipmentService;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestRequestService("/application")
public class ApplicationController {

    private final RentalEquipmentService rentalEquipmentService;

    @PostMapping("/rental/{id}")
    public ResponseEntity<Void> rental(@PathVariable Long id, @RequestBody RentalEquipmentRequest rentalEquipmentRequest) {
        rentalEquipmentService.execute(id, rentalEquipmentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
