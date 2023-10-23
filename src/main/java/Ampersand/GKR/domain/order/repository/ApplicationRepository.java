package Ampersand.GKR.domain.order.repository;

import Ampersand.GKR.domain.equipment.entity.Equipment;
import Ampersand.GKR.domain.order.entity.Application;
import Ampersand.GKR.domain.order.enums.OrderStatus;
import Ampersand.GKR.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUserAndOrderStatus(User user, OrderStatus orderStatus);

    List<Application> findByOrderStatus(OrderStatus orderStatus);

    List<Application> findByRentalEndDateBefore(LocalDateTime currentDate);

    void deleteAllByEquipment(Equipment equipment);
}
