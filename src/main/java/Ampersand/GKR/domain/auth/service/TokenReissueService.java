package Ampersand.GKR.domain.auth.service;

import Ampersand.GKR.domain.auth.entity.RefreshToken;
import Ampersand.GKR.domain.auth.presentation.data.response.NewTokenResponse;
import Ampersand.GKR.domain.auth.repository.RefreshTokenRepository;
import Ampersand.GKR.domain.auth.util.AuthConverter;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.enums.Role;
import Ampersand.GKR.domain.user.exception.UserNotFoundException;
import Ampersand.GKR.domain.user.repository.UserRepository;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.security.exception.RefreshTokenExpirationException;
import Ampersand.GKR.global.security.exception.TokenNotValidException;
import Ampersand.GKR.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RollbackService
@RequiredArgsConstructor
public class TokenReissueService {

    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthConverter authConverter;

    private final UserRepository userRepository;

    public NewTokenResponse execute(String refreshToken) {

        String refresh = tokenProvider.parseToken(refreshToken);

        if(refresh == null) {
            throw new TokenNotValidException();
        }

        String email = tokenProvider.exactEmailFromRefreshToken(refresh);

        Role role = tokenProvider.exactRoleFromRefreshToken(refresh);

        RefreshToken existingRefreshToken = refreshTokenRepository.findById(refresh)
                .orElseThrow(()->new RefreshTokenExpirationException());

        String newAccessToken = tokenProvider.generateAccessToken(email, role);

        String newRefreshToken = tokenProvider.generateRefreshToken(email, role);

        ZonedDateTime accessExp = tokenProvider.accessExpiredTime();

        ZonedDateTime refreshExp = tokenProvider.refreshExpiredTime();

        RefreshToken newRefreshTokenEntity = authConverter.toEntity(existingRefreshToken.getUserId(), newRefreshToken);

        refreshTokenRepository.save(newRefreshTokenEntity);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());

        return NewTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .accessExp(accessExp)
                .refreshExp(refreshExp)
                .build();
    }

}
