package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.presentation.dto.request.EditEquipmentRequest;
import Ampersand.GKR.domain.file.service.FileUploadService;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.EquipmentUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@RollbackService
@RequiredArgsConstructor
public class EditEquipmentService {

    private final UserUtil userUtil;

    private final EquipmentUtil equipmentUtil;

    private final FileUploadService fileUploadService;

    public void execute(Long id, EditEquipmentRequest equipmentRequest, MultipartFile file) {

        User user = userUtil.currentUser();

        Equipment equipment = equipmentUtil.findEquipmentById(id);

        String fileUrl = fileUploadService.execute(file).getImageUrl();

        equipment.update(equipmentRequest, fileUrl);
    }
}