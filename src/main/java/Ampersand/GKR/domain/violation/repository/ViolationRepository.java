package Ampersand.GKR.domain.violation.repository;

import Ampersand.GKR.domain.violation.entity.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViolationRepository extends JpaRepository<Violation, Long>, ViolationRepositoryCustom {

}
