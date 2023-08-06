package Ampersand.GKR.global.util;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.exception.EquipmentNotFoundException;
import Ampersand.GKR.domain.equipment.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentUtil {

    private final EquipmentRepository equipmentRepository;

    public Equipment findEquipmentById(Long id) {

        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFoundException());
    }
}
