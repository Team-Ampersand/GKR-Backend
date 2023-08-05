package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.RentStatus;
import Ampersand.GKR.domain.equipment.presentation.dto.request.CreateEquipmentRequest;
import Ampersand.GKR.domain.equipment.repository.EquipmentRepository;
import Ampersand.GKR.domain.file.service.FileUploadService;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RollbackService
@RequiredArgsConstructor
public class CreateEquipmentService {

    private final UserUtil userUtil;

    private final EquipmentRepository equipmentRepository;

    private final FileUploadService fileUploadService;

    public void execute(CreateEquipmentRequest equipmentRequest, MultipartFile file) {

        User user = userUtil.currentUser();

        String fileUrl = fileUploadService.execute(file).getImageUrl();

        Equipment equipment = Equipment.builder()
                .name(equipmentRequest.getName())
                .description(equipmentRequest.getDescription())
                .imageUrl(fileUrl)
                .rentStatus(RentStatus.NOT_RENT)
                .build();

        equipmentRepository.save(equipment);
    }

}
