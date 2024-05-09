package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.repository.EquipmentRepository;
import Ampersand.GKR.domain.file.service.FileUploadService;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.EquipmentUtil;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;

@RollbackService
@RequiredArgsConstructor
public class DeleteEquipmentService {

    private final EquipmentRepository equipmentRepository;

    private final ApplicationRepository applicationRepository;

    private final FileUploadService fileUploadService;

    private final UserUtil userUtil;

    private final EquipmentUtil equipmentUtil;

    @CacheEvict(cacheNames = "equipmentList", key = "'equipmentList'", cacheManager = "redisCacheManager")
    public void execute(Long id) {

        User user = userUtil.currentUser();

        Equipment equipment = equipmentUtil.findEquipmentById(id);

        if (equipment.getImageUrl() != null) {
            fileUploadService.deleteFile(equipment.getImageUrl());
        }
        applicationRepository.deleteAllByEquipment(equipment);
        equipmentRepository.delete(equipment);
    }
}
