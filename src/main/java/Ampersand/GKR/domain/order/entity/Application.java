package Ampersand.GKR.domain.order.entity;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.enums.OrderType;
import Ampersand.GKR.domain.user.entity.User;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "rental_start_date")
    private LocalDateTime rentalStartDate;

    @Column(name = "rental_end_date")
    private LocalDateTime rentalEndDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setOrderInfo(OrderStatus orderStatus, OrderType orderType) {
        synchronized(this) {
            this.orderStatus = orderStatus;
            this.orderType = orderType;
        }
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        synchronized(this) {
            this.orderStatus = orderStatus;
        }
    }

    public void setRentalDates() {
        synchronized(this) {
            this.rentalStartDate = LocalDateTime.now();
            this.rentalEndDate = rentalStartDate.plusMonths(1);
        }
    }

    public void extensionDate() {
        synchronized(this) {
            this.rentalEndDate = rentalEndDate.plusMonths(1);
        }
    }

    public void extensionReason(String reason) {
        synchronized(this) {
            this.reason = reason;
        }
    }
}
