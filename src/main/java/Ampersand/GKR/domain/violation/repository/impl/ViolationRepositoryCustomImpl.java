package Ampersand.GKR.domain.violation.repository.impl;

import Ampersand.GKR.domain.violation.entity.QViolation;
import Ampersand.GKR.domain.violation.entity.Violation;
import Ampersand.GKR.domain.violation.repository.ViolationRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ViolationRepositoryCustomImpl implements ViolationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Violation> findAllByMostRecentViolation() {

        return Collections.singletonList(jpaQueryFactory.select(QViolation.violation)
                .from(QViolation.violation)
                .where(QViolation.violation.user.isRentalRestricted.isTrue())
                .orderBy(QViolation.violation.violationEndDate.desc())
                .fetchFirst());
    }

    @Override
    public List<Violation> findAllCurrentViolations() {
        return jpaQueryFactory.select(QViolation.violation)
                .from(QViolation.violation)
                .where(QViolation.violation.user.isRentalRestricted.isTrue())
                .where(QViolation.violation.violationEndDate.goe(LocalDateTime.now()))
                .where(QViolation.violation.canceled.isTrue())
                .fetch();
    }

}
