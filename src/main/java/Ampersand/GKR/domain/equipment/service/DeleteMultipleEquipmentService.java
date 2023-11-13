package Ampersand.GKR.domain.equipment.service;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.presentation.dto.request.DeleteMultipleEquipmentRequest;
import Ampersand.GKR.domain.equipment.repository.EquipmentRepository;
import Ampersand.GKR.domain.file.service.FileUploadService;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.EquipmentUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RollbackService
@RequiredArgsConstructor
public class DeleteMultipleEquipmentService {

    private final EquipmentRepository equipmentRepository;

    private final ApplicationRepository applicationRepository;

    private final FileUploadService fileUploadService;

    private final EquipmentUtil equipmentUtil;

    public void execute(List<Long> deleteMultipleEquipmentRequest) {

        for (Long equipmentId : deleteMultipleEquipmentRequest) {

            Equipment equipment = equipmentUtil.findEquipmentById(equipmentId);

            if (equipment.getImageUrl() != null) {
                fileUploadService.deleteFile(equipment.getImageUrl());
            }

            applicationRepository.deleteAllByEquipment(equipment);

            equipmentRepository.delete(equipment);
        }
    }
}
