package Ampersand.GKR.domain.user.service;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.presentation.dto.response.UserInfoResponse;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class UserInfoService {

    private final UserUtil userUtil;

    public UserInfoResponse execute() {

        User user = userUtil.currentUser();

        UserInfoResponse userInfoResponse = UserInfoResponse.builder()
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

        return userInfoResponse;
    }
}