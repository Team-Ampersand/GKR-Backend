package Ampersand.GKR.domain.notice.presentation;

import Ampersand.GKR.domain.equipment.presentation.dto.request.CreateEquipmentRequest;
import Ampersand.GKR.domain.equipment.service.CreateEquipmentService;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RestRequestService("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final CreateEquipmentService createEquipmentService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestPart(name = "notice") CreateEquipmentRequest createEquipmentRequest, @RequestPart(name = "file") MultipartFile file) {
        createEquipmentService.execute(createEquipmentRequest, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
