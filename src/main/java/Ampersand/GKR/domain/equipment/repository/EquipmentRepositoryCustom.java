package Ampersand.GKR.domain.equipment.repository;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.equipment.enums.EquipmentType;

import java.util.List;

public interface EquipmentRepositoryCustom {

    List<Equipment> findByName(String name);

    List<Equipment> filter(EquipmentStatus equipmentStatus, EquipmentType equipmentType);
}
