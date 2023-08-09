package Ampersand.GKR.domain.user.service;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.presentation.dto.response.ListAllUserInfoResponse;
import Ampersand.GKR.domain.user.repository.UserRepository;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.user.presentation.dto.response.UserInfoResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListAllUserInfoService {

    private final UserRepository userRepository;

    public ListAllUserInfoResponse execute() {

        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "stuNum"));

        ListAllUserInfoResponse listAllUserInfoResponse = ListAllUserInfoResponse.builder()
                .usersList(
                        users.stream()
                                .map(user -> toResponse(user))
                                .collect(Collectors.toList())
                )
                .build();

        return listAllUserInfoResponse;
    }
}
