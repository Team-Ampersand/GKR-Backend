package Ampersand.GKR.domain.auth.presentation;

import Ampersand.GKR.domain.auth.presentation.data.dto.LoginDto;
import Ampersand.GKR.domain.auth.presentation.data.request.LoginRequest;
import Ampersand.GKR.domain.auth.presentation.data.response.LoginResponse;
import Ampersand.GKR.domain.auth.presentation.data.response.NewTokenResponse;
import Ampersand.GKR.domain.auth.service.TokenReissueService;
import Ampersand.GKR.domain.auth.service.UserLoginService;
import Ampersand.GKR.domain.auth.service.UserLogoutService;
import Ampersand.GKR.domain.auth.util.AuthConverter;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestRequestService("/auth")
public class AuthController {

    private final UserLoginService userLoginService;

    private final UserLogoutService userLogoutService;

    private final TokenReissueService tokenReissueService;

    private final AuthConverter authConverter;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws IOException {

        LoginDto loginDto = authConverter.toDto(loginRequest);

        LoginResponse loginResponse = userLoginService.execute(loginDto);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(){

        userLogoutService.execute();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("Refresh-Token") String refreshToken) {

        NewTokenResponse newTokenResponse = tokenReissueService.execute(refreshToken);

        return new ResponseEntity<>(newTokenResponse, HttpStatus.OK);
    }
}
