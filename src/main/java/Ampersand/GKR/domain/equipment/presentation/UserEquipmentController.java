package Ampersand.GKR.domain.equipment.presentation;

import Ampersand.GKR.domain.equipment.presentation.dto.response.ListEquipmentResponse;
import Ampersand.GKR.domain.equipment.service.ListEquipmentService;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestRequestService("/user/equipment")
public class UserEquipmentController {

    private final ListEquipmentService listEquipmentService;

    @GetMapping
    public ResponseEntity<ListEquipmentResponse> list() {
        var list = listEquipmentService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
