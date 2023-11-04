package Ampersand.GKR.domain.user.service;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.repository.ApplicationRepository;
import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.user.presentation.dto.response.ListMyRentalEquipmentResponse;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.user.presentation.dto.response.MyRentalEquipmentResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListMyRentalEquipmentService {

    private final UserUtil userUtil;

    private final ApplicationRepository applicationRepository;

    public ListMyRentalEquipmentResponse execute() {

        User user = userUtil.currentUser();

        List<Application> applications = applicationRepository.findAllByUserAndOrderStatus(user, OrderStatus.ACCEPT);

        ListMyRentalEquipmentResponse listMyRentalEquipmentResponse = ListMyRentalEquipmentResponse.builder()
                .rentalList(
                        applications.stream()
                                .map(application -> toResponse(application))
                                .collect(Collectors.toList())
                )
                .build();

        return listMyRentalEquipmentResponse;
    }
}
