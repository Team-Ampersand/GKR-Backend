package Ampersand.GKR.domain.notice.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class DetailNoticeResponse {

    private Long id;

    private String title;

    private String content;

    private String imageUrl;

    private LocalDateTime createNoticeDate;
}
