package Ampersand.GKR.domain.notice.service;

import Ampersand.GKR.domain.notice.entity.Notice;
import Ampersand.GKR.domain.notice.repository.NoticeRepository;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.NoticeUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class DeleteNoticeService {

    private final NoticeUtil noticeUtil;

    private final NoticeRepository noticeRepository;

    public void execute(Long id) {

        Notice notice = noticeUtil.findNoticeById(id);

        noticeRepository.delete(notice);
    }
}
