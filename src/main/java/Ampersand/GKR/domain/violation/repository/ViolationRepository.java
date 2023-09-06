package Ampersand.GKR.domain.violation.repository;

import Ampersand.GKR.domain.user.entity.User;
import Ampersand.GKR.domain.violation.entity.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, Long>, ViolationRepositoryCustom {

    List<Violation> findByUser(User user);
}
