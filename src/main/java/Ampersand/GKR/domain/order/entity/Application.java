package Ampersand.GKR.domain.order.entity;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.order.enums.RentalStatus;
import Ampersand.GKR.domain.order.enums.RentalType;
import Ampersand.GKR.domain.user.entity.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "rental_date")
    private String rentalDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.STRING)
    private RentalType rentalType;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    @OneToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}