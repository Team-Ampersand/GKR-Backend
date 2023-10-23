package Ampersand.GKR.domain.notice.service;

import Ampersand.GKR.domain.notice.entity.Notice;
import Ampersand.GKR.domain.notice.presentation.dto.response.DetailNoticeResponse;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import Ampersand.GKR.global.util.NoticeUtil;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class DetailNoticeService {

    private final NoticeUtil noticeUtil;

    public DetailNoticeResponse execute(Long id) {

        Notice notice = noticeUtil.findNoticeById(id);

        DetailNoticeResponse detailNoticeResponse = DetailNoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .imageUrl(notice.getImageUrl())
                .createNoticeDate(notice.getCreateNoticeDate())
                .build();

        return detailNoticeResponse;
    }
}
