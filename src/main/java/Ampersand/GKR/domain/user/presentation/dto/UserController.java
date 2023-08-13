package Ampersand.GKR.domain.user.presentation.dto;

import Ampersand.GKR.domain.user.presentation.dto.response.ListAllUserInfoResponse;
import Ampersand.GKR.domain.user.presentation.dto.response.UserInfoResponse;
import Ampersand.GKR.domain.user.service.ListAllUserInfoService;
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

    private final ListAllUserInfoService listAllUserInfoService;

    @GetMapping
    public ResponseEntity<UserInfoResponse> getUserInfo() {
        UserInfoResponse userInfoResponse = userInfoService.execute();
        return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ListAllUserInfoResponse> getAllUserInfo() {
        ListAllUserInfoResponse listAllUserInfoResponse = listAllUserInfoService.execute();
        return new ResponseEntity<>(listAllUserInfoResponse, HttpStatus.OK);
    }
}