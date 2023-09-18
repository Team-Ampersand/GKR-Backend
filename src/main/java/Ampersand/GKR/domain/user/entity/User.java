package Ampersand.GKR.domain.user.entity;

import Ampersand.GKR.domain.user.enums.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "student_name" , nullable = false)
    private String name;

    @Column(name = "grade")
    private int grade;

    @Column(name = "class_num")
    private int classNum;

    @Column(name = "student_num")
    private int stuNum;

    @Column(name = "profile_url")
    private String profileUrl;

    private boolean isRentalRestricted;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setRentalRestricted(Boolean restricted) {
        this.isRentalRestricted = restricted;
    }
}

