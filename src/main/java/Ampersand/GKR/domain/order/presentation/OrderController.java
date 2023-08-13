package Ampersand.GKR.domain.order.presentation;

import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.presentation.dto.request.RentalEquipmentRequest;
import Ampersand.GKR.domain.order.presentation.dto.response.ListOrderEquipmentResponse;
import Ampersand.GKR.domain.order.service.ListOrderStatusEquipmentService;
import Ampersand.GKR.domain.order.service.RentalEquipmentService;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestRequestService("/order")
public class OrderController {

    private final RentalEquipmentService rentalEquipmentService;

    private final ListOrderStatusEquipmentService listOrderStatusEquipmentService;

    @PostMapping("/rental/{id}")
    public ResponseEntity<Void> rental(@PathVariable Long id, @RequestBody RentalEquipmentRequest rentalEquipmentRequest) {
        rentalEquipmentService.execute(id, rentalEquipmentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/state")
    public ResponseEntity<ListOrderEquipmentResponse> orderStateList(@RequestParam OrderStatus orderStatus) {
        var list = listOrderStatusEquipmentService.execute(orderStatus);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
