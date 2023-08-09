package Ampersand.GKR.domain.user.presentation.dto;

import Ampersand.GKR.domain.user.presentation.dto.response.UserInfoResponse;
import Ampersand.GKR.domain.user.service.UserInfoService;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestRequestService("/user")
public class UserController {

    private final UserInfoService userInfoService;

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo() {
        UserInfoResponse userInfoResponse = userInfoService.execute();
        return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
    }
}