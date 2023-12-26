package Ampersand.GKR.domain.equipment.presentation;

import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
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

import java.util.List;

@RequiredArgsConstructor
@RestRequestService("/equipment")
public class EquipmentController {

    private final CreateEquipmentService createEquipmentService;

    private final ListEquipmentService listEquipmentService;

    private final ListStatusEquipmentService listStatusEquipmentService;

    private final ListTypeEquipmentService listTypeEquipmentService;

    private final ListFilterEquipmentService listFilterEquipmentService;

    private final DeleteEquipmentService deleteEquipmentService;

    private final DetailEquipmentService detailEquipmentService;

    private final EditEquipmentService editEquipmentService;

    private final ListSearchEquipmentNameService listSearchEquipmentNameService;

    private final RepairEquipmentService repairEquipmentService;

    private final RepairCompletionEquipmentService repairCompletionEquipmentService;

    private final DeleteMultipleEquipmentService deleteMultipleEquipmentService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestPart(name = "equipment") CreateEquipmentRequest createEquipmentRequest, @RequestPart(name = "file", required = false) MultipartFile file) {
        createEquipmentService.execute(createEquipmentRequest, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListEquipmentResponse> allList() {
        var list = listEquipmentService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailEquipmentResponse> detail(@PathVariable Long id) {
        DetailEquipmentResponse detailEquipmentResponse = detailEquipmentService.execute(id);
        return new ResponseEntity<>(detailEquipmentResponse, HttpStatus.OK);
    }

    @GetMapping("/state")
    public ResponseEntity<ListEquipmentResponse> eqStatusList(@RequestParam EquipmentStatus equipmentStatus) {
        var list = listStatusEquipmentService.execute(equipmentStatus);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<ListEquipmentResponse> eqTypeList(@RequestParam EquipmentType equipmentType) {
        var list = listTypeEquipmentService.execute(equipmentType);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<ListEquipmentResponse> filter(
            @RequestParam(name = "state", required = false) EquipmentStatus equipmentStatus,
            @RequestParam(name = "type", required = false) EquipmentType equipmentType)
    {
        var list = listFilterEquipmentService.execute(equipmentStatus, equipmentType);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ListEquipmentResponse> search(@RequestParam("name") SearchEquipmentNameRequest searchEquipmentNameRequest) {
        var list = listSearchEquipmentNameService.execute(searchEquipmentNameRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteEquipmentService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMultiple(@RequestParam("equipmentIdList") List<Long> deleteMultipleEquipmentRequest) {
        deleteMultipleEquipmentService.execute(deleteMultipleEquipmentRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestPart(name = "equipment") EditEquipmentRequest editEquipmentRequest, @RequestPart(name = "file", required = false) MultipartFile file) {
        editEquipmentService.execute(id, editEquipmentRequest, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/repair/{id}")
    public ResponseEntity<Void> repair(@PathVariable Long id) {
        repairEquipmentService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/repair/completion/{id}")
    public ResponseEntity<Void> repairCompletion(@PathVariable Long id) {
        repairCompletionEquipmentService.execute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
