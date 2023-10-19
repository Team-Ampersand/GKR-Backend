package Ampersand.GKR.global.util;

import Ampersand.GKR.domain.notice.entity.Notice;
import Ampersand.GKR.domain.notice.exception.NoticeNotFoundException;
import Ampersand.GKR.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeUtil {

    private final NoticeRepository noticeRepository;

    public Notice findNoticeById(Long id) {

        return noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException());
    }
}
