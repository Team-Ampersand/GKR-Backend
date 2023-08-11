package Ampersand.GKR.domain.order.repository;

import Ampersand.GKR.domain.order.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Application, Long> {
}
