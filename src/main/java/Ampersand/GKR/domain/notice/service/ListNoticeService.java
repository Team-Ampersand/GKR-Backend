package Ampersand.GKR.domain.notice.service;

import Ampersand.GKR.domain.notice.entity.Notice;
import Ampersand.GKR.domain.notice.presentation.dto.response.ListNoticeResponse;
import Ampersand.GKR.domain.notice.repository.NoticeRepository;
import Ampersand.GKR.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static Ampersand.GKR.domain.notice.presentation.dto.response.NoticeResponse.toResponse;

@ReadOnlyService
@RequiredArgsConstructor
public class ListNoticeService {

    private final NoticeRepository noticeRepository;

    public ListNoticeResponse execute() {

        List<Notice> notices = noticeRepository.findAllByOrderByCreateNoticeDateDesc();

        ListNoticeResponse listNoticeResponse = ListNoticeResponse.builder()
                .noticeList(
                        notices.stream()
                                .map(notice -> toResponse(notice))
                                .collect(Collectors.toList())
                )
                .build();

        return listNoticeResponse;
    }
}
