package Ampersand.GKR.domain.auth.util;

import Ampersand.GKR.domain.auth.entity.RefreshToken;
import Ampersand.GKR.domain.auth.repository.RefreshTokenRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.repository.UserRepository;
import gauth.GAuthUserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthUtil {

    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthConverter authConverter;

    private final UserRepository userRepository;

    public void saveNewUser(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User signInUserInfo = authConverter.toEntity(gAuthUserInfo);

        userRepository.save(signInUserInfo);

        saveNewRefreshToken(signInUserInfo, refreshToken);
    }

    public void saveNewAdmin(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User signInAdminInfo = authConverter.toAdminEntity(gAuthUserInfo);

        userRepository.save(signInAdminInfo);

        saveNewRefreshToken(signInAdminInfo, refreshToken);
    }

    public void saveNewTeacher(GAuthUserInfo gAuthUserInfo, String refreshToken) {

        User signInAdminInfo = authConverter.toTeacherEntity(gAuthUserInfo);

        userRepository.save(signInAdminInfo);

        saveNewRefreshToken(signInAdminInfo, refreshToken);
    }

    public RefreshToken saveNewRefreshToken(User userInfo, String refreshToken) {

        RefreshToken newRefreshToken = authConverter.toEntity(userInfo, refreshToken);

        return refreshTokenRepository.save(newRefreshToken);
    }
}
