package Ampersand.GKR.domain.violation.repository;

import Ampersand.GKR.domain.violation.entity.Violation;

import java.util.List;

public interface ViolationRepositoryCustom {

    List<Violation> findAllByMostRecentViolation();

    List<Violation> findAllCurrentViolations();
}
