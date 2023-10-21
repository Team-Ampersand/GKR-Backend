package Ampersand.GKR.domain.notice.presentation.dto.response;

import Ampersand.GKR.domain.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class NoticeResponse {

    private Long id;

    private String title;

    private String imageUrl;

    private LocalDateTime createNoticeDate;

    public static NoticeResponse toResponse(Notice notice) {

        return NoticeResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .imageUrl(notice.getImageUrl())
                .createNoticeDate(notice.getCreateNoticeDate())
                .build();
    }
}
