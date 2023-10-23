package Ampersand.GKR.domain.notice.repository;

import Ampersand.GKR.domain.notice.entity.Notice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice, Long> {

    List<Notice> findAllByOrderByCreateNoticeDateDesc();
}
