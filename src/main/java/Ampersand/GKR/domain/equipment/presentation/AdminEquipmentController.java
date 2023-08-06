package Ampersand.GKR.domain.equipment.presentation;

import Ampersand.GKR.domain.equipment.presentation.dto.request.CreateEquipmentRequest;
import Ampersand.GKR.domain.equipment.presentation.dto.response.ListEquipmentResponse;
import Ampersand.GKR.domain.equipment.service.CreateEquipmentService;
import Ampersand.GKR.domain.equipment.service.DeleteEquipmentService;
import Ampersand.GKR.domain.equipment.service.ListEquipmentService;
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

    private final DeleteEquipmentService deleteEquipmentService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteEquipmentService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
