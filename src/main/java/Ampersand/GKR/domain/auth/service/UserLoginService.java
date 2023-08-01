package Ampersand.GKR.domain.auth.service;

import Ampersand.GKR.domain.auth.exception.RoleNotExistException;
import Ampersand.GKR.domain.auth.presentation.data.dto.LoginDto;
import Ampersand.GKR.domain.auth.presentation.data.response.LoginResponse;
import Ampersand.GKR.domain.auth.util.AuthUtil;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.enums.Role;
import Ampersand.GKR.domain.user.repository.UserRepository;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.gauth.properties.GAuthProperties;
import Ampersand.GKR.global.security.jwt.TokenProvider;
import gauth.GAuth;
import gauth.GAuthToken;
import gauth.GAuthUserInfo;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.time.ZonedDateTime;

@RollbackService
@RequiredArgsConstructor
public class UserLoginService {

    private final GAuthProperties gAuthProperties;

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    private final GAuth gAuth;

    private final AuthUtil authUtil;

    public LoginResponse execute(LoginDto loginDto) throws IOException {

        GAuthToken gAuthToken = gAuth.generateToken(loginDto.getCode(), gAuthProperties.getClientId(), gAuthProperties.getClientSecret(), gAuthProperties.getRedirectUri());

        GAuthUserInfo gAuthUserInfo = gAuth.getUserInfo(gAuthToken.getAccessToken());

        Role role = getRoleByGAuthInfo(gAuthUserInfo.getRole(), gAuthUserInfo.getEmail());

        String accessToken = tokenProvider.generateAccessToken(gAuthUserInfo.getEmail(), role);

        String refreshToken = tokenProvider.generateRefreshToken(gAuthUserInfo.getEmail(), role);

        ZonedDateTime accessExp = tokenProvider.accessExpiredTime();

        ZonedDateTime refreshExp = tokenProvider.refreshExpiredTime();

        if(role == Role.ROLE_ADMIN) {

            createAdminOrRefreshToken(gAuthUserInfo, refreshToken);
        } else {

            createUserOrRefreshToken(gAuthUserInfo, refreshToken);
        }

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExp(accessExp)
                .refreshExp(refreshExp)
                .build();
    }

    private Role getRoleByGAuthInfo(String role, String email) {

        String secretEmail = System.getenv("SECRET_EMAIL");

        if (email.equals(secretEmail)) {
            return Role.ROLE_ADMIN;
        }

        User user = userRepository.findUserByEmail(email);

        if(user == null) {
            switch (role) {
                case "ROLE_STUDENT":
                    return Role.ROLE_STUDENT;
                case "ROLE_ADMIN":
                    return Role.ROLE_ADMIN;
                default:
                    throw new RoleNotExistException();
            }
        }

        if(user.getRole().equals(Role.ROLE_ADMIN)) {

            return Role.ROLE_ADMIN;
        }
        return Role.ROLE_STUDENT;
    }

    private void createUserOrRefreshToken(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User userInfo = userRepository.findUserByEmail(gAuthUserInfo.getEmail());

        if(userInfo == null) {

            authUtil.saveNewUser(gAuthUserInfo, refreshToken);
        } else {

            authUtil.saveNewRefreshToken(userInfo, refreshToken);
        }
    }

    private void createAdminOrRefreshToken(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User adminInfo = userRepository.findUserByEmail(gAuthUserInfo.getEmail());

        if(adminInfo == null) {

            authUtil.saveNewAdmin(gAuthUserInfo, refreshToken);
        } else {

            authUtil.saveNewRefreshToken(adminInfo, refreshToken);
        }
    }
}
