package Ampersand.GKR.domain.user.service;

import Ampersand.GKR.domain.equipment.presentation.dto.response.ListEquipmentResponse;
import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.equipment.presentation.dto.response.EquipmentResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListMyRentalEquipmentService {

    private final UserUtil userUtil;

    private final ApplicationRepository applicationRepository;

    public ListEquipmentResponse execute() {

        User user = userUtil.currentUser();

        List<Application> applications = applicationRepository.findAllByUserAndOrderStatus(user, OrderStatus.ACCEPT);

        ListEquipmentResponse listEquipmentResponse = ListEquipmentResponse.builder()
                .equipmentList(
                        applications.stream()
                                .map(application -> toResponse(application))
                                .collect(Collectors.toList())
                )
                .build();

        return listEquipmentResponse;
    }
}
