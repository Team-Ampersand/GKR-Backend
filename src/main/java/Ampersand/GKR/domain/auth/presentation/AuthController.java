package Ampersand.GKR.domain.auth.presentation;

import Ampersand.GKR.domain.auth.presentation.data.dto.LoginDto;
import Ampersand.GKR.domain.auth.presentation.data.request.LoginRequest;
import Ampersand.GKR.domain.auth.presentation.data.response.LoginResponse;
import Ampersand.GKR.domain.auth.service.UserLoginService;
import Ampersand.GKR.domain.auth.util.AuthConverter;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestRequestService("/auth")
public class AuthController {

    private final UserLoginService userLoginService;

    private final AuthConverter authConverter;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws IOException {

        LoginDto loginDto = authConverter.toDto(loginRequest);

        LoginResponse loginResponse = userLoginService.execute(loginDto);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
