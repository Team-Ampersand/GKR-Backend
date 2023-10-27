package Ampersand.GKR.domain.violation.entity;

import Ampersand.GKR.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Violation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "violation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String violationReason;

    @Column(name = "violation_start_date")
    private LocalDateTime violationStartDate;

    @Column(name = "violation_end_date")
    private LocalDateTime violationEndDate;

    public void setViolationDates() {
        this.violationStartDate = LocalDateTime.now();
        this.violationEndDate = violationStartDate.plusMonths(1);
    }
}
