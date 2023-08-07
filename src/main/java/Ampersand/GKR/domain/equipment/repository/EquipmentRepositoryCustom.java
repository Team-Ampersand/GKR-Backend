package Ampersand.GKR.domain.equipment.repository;

import Ampersand.GKR.domain.equipment.entity.Equipment;

import java.util.List;

public interface EquipmentRepositoryCustom {

    List<Equipment> findByName(String name);
}
