package Ampersand.GKR.domain.equipment.repository;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
