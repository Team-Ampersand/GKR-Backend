package Ampersand.GKR.domain.user.presentation.dto.response;

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
}
