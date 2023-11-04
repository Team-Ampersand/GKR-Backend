package Ampersand.GKR.domain.order.presentation;

import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.presentation.dto.request.ExtensionEquipmentRequest;
import Ampersand.GKR.domain.order.presentation.dto.request.RentalEquipmentRequest;
import Ampersand.GKR.domain.order.presentation.dto.response.ApplicationResponse;
import Ampersand.GKR.domain.order.presentation.dto.response.ListApplicationResponse;
import Ampersand.GKR.domain.order.presentation.dto.response.ListOrderEquipmentResponse;
import Ampersand.GKR.domain.order.service.*;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestRequestService("/order")
public class OrderController {

    private final RentalEquipmentService rentalEquipmentService;

    private final ReturnEquipmentService returnEquipmentService;

    private final ExtensionEquipmentService extensionEquipmentService;

    private final RejectOrderService rejectOrderService;

    private final AcceptOrderService acceptOrderService;

    private final ListOrderStatusEquipmentService listOrderStatusEquipmentService;

    private final ListCurrentRentalEquipmentService listCurrentRentalEquipmentService;

    private final ListWaitingOrderEquipmentService listWaitingOrderEquipmentService;

    private final ListNotReturnUserEquipmentService listNotReturnUserEquipmentService;

    private final OrderCancelService orderCancelService;

    private final DetailOrderService detailOrderService;


    @PostMapping("/rental/{id}")
    public ResponseEntity<Void> eqRental(@PathVariable Long id, @RequestBody RentalEquipmentRequest rentalEquipmentRequest) {
        rentalEquipmentService.execute(id, rentalEquipmentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<Void> eqReturn(@PathVariable Long id) {
        returnEquipmentService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/extension/{id}")
    public ResponseEntity<Void> eqExtension(@PathVariable Long id, @RequestBody ExtensionEquipmentRequest extensionEquipmentRequest) {
        extensionEquipmentService.execute(id, extensionEquipmentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        orderCancelService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/reject/{id}")
    public ResponseEntity<Void> reject(@PathVariable Long id) {
        rejectOrderService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/accept/{id}")
    public ResponseEntity<Void> accept(@PathVariable Long id) {
        acceptOrderService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/state")
    public ResponseEntity<ListOrderEquipmentResponse> orderStateList(@RequestParam OrderStatus orderStatus) {
        var list = listOrderStatusEquipmentService.execute(orderStatus);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/now")
    public ResponseEntity<ListApplicationResponse> notReturnList() {
        var list = listCurrentRentalEquipmentService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/wait")
    public ResponseEntity<ListApplicationResponse> waitingList() {
        var list = listWaitingOrderEquipmentService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/noreturn")
    public ResponseEntity<ListApplicationResponse> noReturnList() {
        var list = listNotReturnUserEquipmentService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ApplicationResponse> detail(@PathVariable Long id) {
        ApplicationResponse applicationResponse = detailOrderService.execute(id);
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }
}
