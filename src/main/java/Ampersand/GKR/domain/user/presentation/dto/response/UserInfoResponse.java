package Ampersand.GKR.domain.user.presentation.dto.response;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UserInfoResponse {

    private UUID id;

    private String email;

    private String name;

    private int grade;

    private int classNum;

    private int stuNum;

    private String profileUrl;

    private boolean isRentalRestricted;

    private Role role;

    public static UserInfoResponse toResponse(User user) {

        return UserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .grade(user.getGrade())
                .classNum(user.getClassNum())
                .stuNum(user.getStuNum())
                .profileUrl(user.getProfileUrl())
                .isRentalRestricted(user.isRentalRestricted())
                .role(user.getRole())
                .build();
    }
}
