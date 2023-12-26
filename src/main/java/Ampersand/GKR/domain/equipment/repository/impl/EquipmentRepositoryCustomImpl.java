package Ampersand.GKR.domain.equipment.repository.impl;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.equipment.entity.QEquipment;
import Ampersand.GKR.domain.equipment.enums.EquipmentStatus;
import Ampersand.GKR.domain.equipment.enums.EquipmentType;
import Ampersand.GKR.domain.equipment.repository.EquipmentRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EquipmentRepositoryCustomImpl implements EquipmentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Equipment> findByName(String name) {

        return jpaQueryFactory
                .selectFrom(QEquipment.equipment)
                .where(QEquipment.equipment.name.contains(name))
                .orderBy(QEquipment.equipment.name.asc())
                .fetch();
    }

    @Override
    public List<Equipment> filter(EquipmentStatus equipmentStatus, EquipmentType equipmentType) {

        return jpaQueryFactory
                .selectFrom(QEquipment.equipment)
                .where(
                        equipmentStatusEq(equipmentStatus),
                        equipmentTypeEq(equipmentType)
                )
                .orderBy(QEquipment.equipment.name.asc())
                .fetch();
    }

    private BooleanExpression equipmentStatusEq(EquipmentStatus equipmentStatus) {
        return equipmentStatus != null ? QEquipment.equipment.equipmentStatus.eq(equipmentStatus) : null;
    }

    private BooleanExpression equipmentTypeEq(EquipmentType equipmentType) {
        return equipmentType != null ? QEquipment.equipment.equipmentType.eq(equipmentType) : null;
    }
}
