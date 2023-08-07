package Ampersand.GKR.domain.equipment.entity;

import Ampersand.GKR.domain.equipment.enums.RentStatus;
import Ampersand.GKR.domain.equipment.presentation.dto.request.EditEquipmentRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus;

    public void update(EditEquipmentRequest equipmentRequest, String fileUrl) {
        this.name = equipmentRequest.getName();
        this.description = equipmentRequest.getDescription();
        this.imageUrl = fileUrl;
    }
}
