package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.presentation.dto.response.ListApplicationResponse;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.order.presentation.dto.response.ApplicationResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListNotReturnUserEquipmentService {

    private final ApplicationRepository applicationRepository;

    private final UserUtil userUtil;

    public ListApplicationResponse execute() {

        User user = userUtil.currentUser();

        List<Application> applications = applicationRepository.findByRentalEndDateBefore(LocalDateTime.now());

        ListApplicationResponse listApplicationResponse = ListApplicationResponse.builder()
                .applicationList(
                        applications.stream()
                                .map(application -> toResponse(application))
                                .collect(Collectors.toList())
                )
                .build();

        return listApplicationResponse;
    }
}
