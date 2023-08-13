package Ampersand.GKR.domain.order.service;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.presentation.dto.response.ListOrderEquipmentResponse;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.order.presentation.dto.response.OrderEquipmentResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListOrderStatusEquipmentService {

    private final ApplicationRepository applicationRepository;

    private final UserUtil userUtil;

    public ListOrderEquipmentResponse execute(OrderStatus orderStatus) {

        User user = userUtil.currentUser();

        List<Application> applications = applicationRepository.findByUserAndOrderStatus(user, orderStatus);

        ListOrderEquipmentResponse listOrderEquipmentResponse = ListOrderEquipmentResponse.builder()
                .orderEquipmentList(
                        applications.stream()
                                .map(application -> toResponse(application))
                                .collect(Collectors.toList())
                )
                .build();

        return listOrderEquipmentResponse;
    }
}
