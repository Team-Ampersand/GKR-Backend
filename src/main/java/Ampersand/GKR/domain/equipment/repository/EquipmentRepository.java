package Ampersand.GKR.domain.equipment.repository;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>, EquipmentRepositoryCustom{

    List<Equipment> findByEquipmentStatus(EquipmentStatus equipmentStatus);

    List<Equipment> findByEquipmentType(EquipmentType equipmentType);
}
