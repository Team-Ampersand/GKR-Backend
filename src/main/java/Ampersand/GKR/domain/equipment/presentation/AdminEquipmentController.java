package Ampersand.GKR.domain.equipment.presentation;

import Ampersand.GKR.domain.equipment.enums.RentStatus;
import Ampersand.GKR.domain.equipment.presentation.dto.request.CreateEquipmentRequest;
import Ampersand.GKR.domain.equipment.presentation.dto.request.EditEquipmentRequest;
import Ampersand.GKR.domain.equipment.presentation.dto.response.DetailEquipmentResponse;
import Ampersand.GKR.domain.equipment.presentation.dto.response.ListEquipmentResponse;
import Ampersand.GKR.domain.equipment.service.*;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestRequestService("/admin/equipment")
public class AdminEquipmentController {

    private final CreateEquipmentService createEquipmentService;

    private final ListEquipmentService listEquipmentService;

    private final ListNotRentEquipmentService listNotRentEquipmentService;

    private final DeleteEquipmentService deleteEquipmentService;

    private final DetailEquipmentService detailEquipmentService;

    private final EditEquipmentService editEquipmentService;


    @PostMapping
    public ResponseEntity<Void> create(@RequestPart(name = "equipment") CreateEquipmentRequest createEquipmentRequest, @RequestPart(name = "file") MultipartFile file) {
        createEquipmentService.execute(createEquipmentRequest, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListEquipmentResponse> list() {
        var list = listEquipmentService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailEquipmentResponse> detail(@PathVariable Long id) {
        DetailEquipmentResponse detailEquipmentResponse = detailEquipmentService.execute(id);
        return new ResponseEntity<>(detailEquipmentResponse, HttpStatus.OK);
    }

    @GetMapping("/rent")
    public ResponseEntity<ListEquipmentResponse> rentStatusList(@RequestParam RentStatus rentStatus) {
        var list = listNotRentEquipmentService.execute(rentStatus);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteEquipmentService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestPart(name = "equipment") EditEquipmentRequest editEquipmentRequest, @RequestPart(name = "file") MultipartFile file) {
        editEquipmentService.execute(id, editEquipmentRequest, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
