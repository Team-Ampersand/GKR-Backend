package Ampersand.GKR.domain.equipment.presentation;

import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import Ampersand.GKR.domain.equipment.enums.RentStatus;
import Ampersand.GKR.domain.equipment.presentation.dto.request.CreateEquipmentRequest;
import Ampersand.GKR.domain.equipment.presentation.dto.request.EditEquipmentRequest;
import Ampersand.GKR.domain.equipment.presentation.dto.request.SearchEquipmentNameRequest;
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
@RestRequestService("/equipment")
public class EquipmentController {

    private final CreateEquipmentService createEquipmentService;

    private final ListEquipmentService listEquipmentService;

    private final ListRentStatusEquipmentService listRentStatusEquipmentService;

    private final ListEqTypeEquipmentService listEqTypeEquipmentService;

    private final DeleteEquipmentService deleteEquipmentService;

    private final DetailEquipmentService detailEquipmentService;

    private final EditEquipmentService editEquipmentService;

    private final ListSearchEquipmentNameService listSearchEquipmentNameService;


    @PostMapping("/admin/create")
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
        var list = listRentStatusEquipmentService.execute(rentStatus);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<ListEquipmentResponse> rentStatusList(@RequestParam EquipmentType equipmentType) {
        var list = listEqTypeEquipmentService.execute(equipmentType);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ListEquipmentResponse> searchName(@RequestParam("name") SearchEquipmentNameRequest searchEquipmentNameRequest) {
        var list = listSearchEquipmentNameService.execute(searchEquipmentNameRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteEquipmentService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/admin/edit")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestPart(name = "equipment") EditEquipmentRequest editEquipmentRequest, @RequestPart(name = "file") MultipartFile file) {
        editEquipmentService.execute(id, editEquipmentRequest, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
