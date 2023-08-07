package Ampersand.GKR.domain.equipment.presentation;

import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import Ampersand.GKR.domain.equipment.enums.RentStatus;
import Ampersand.GKR.domain.equipment.presentation.dto.request.SearchEquipmentNameRequest;
import Ampersand.GKR.domain.equipment.presentation.dto.response.DetailEquipmentResponse;
import Ampersand.GKR.domain.equipment.presentation.dto.response.ListEquipmentResponse;
import Ampersand.GKR.domain.equipment.service.*;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RestRequestService("/user/equipment")
public class UserEquipmentController {

    private final ListEquipmentService listEquipmentService;

    private final ListRentStatusEquipmentService listRentStatusEquipmentService;

    private final ListEqTypeEquipmentService listEqTypeEquipmentService;

    private final DetailEquipmentService detailEquipmentService;

    private final ListSearchEquipmentNameService listSearchEquipmentNameService;

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
}
