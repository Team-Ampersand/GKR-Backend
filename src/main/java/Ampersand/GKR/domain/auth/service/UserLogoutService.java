package Ampersand.GKR.domain.auth.service;

import Ampersand.GKR.domain.auth.entity.RefreshToken;
import Ampersand.GKR.domain.auth.repository.RefreshTokenRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.exception.UserNotFoundException;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class UserLogoutService {

    private final UserUtil userUtil;

    private final RefreshTokenRepository refreshTokenRepository;

    public void execute() {

        User userInfo = userUtil.currentUser();

        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userInfo.getId());

        if(refreshToken == null) {

            throw new UserNotFoundException();
        }

        refreshTokenRepository.delete(refreshToken);
    }
}
