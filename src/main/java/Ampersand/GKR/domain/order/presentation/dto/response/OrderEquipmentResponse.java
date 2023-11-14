package Ampersand.GKR.domain.order.presentation.dto.response;

import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.order.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderEquipmentResponse {

    private Long equipmentId;

    private Long applicationId;

    private String name;

    private String imageUrl;

    private String description;

    private OrderStatus orderStatus;

    private OrderType orderType;

    public static OrderEquipmentResponse toResponse(Application application) {

        return OrderEquipmentResponse.builder()
                .equipmentId(application.getEquipment().getId())
                .applicationId(application.getId())
                .name(application.getEquipment().getName())
                .imageUrl(application.getEquipment().getImageUrl())
                .description(application.getEquipment().getDescription())
                .orderStatus(application.getOrderStatus())
                .orderType(application.getOrderType())
                .build();
    }
}
