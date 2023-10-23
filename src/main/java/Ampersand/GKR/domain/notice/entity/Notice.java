package Ampersand.GKR.domain.notice.entity;

import Ampersand.GKR.domain.notice.presentation.dto.request.EditNoticeRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "content")
    private String content;

    @Column(name = "create_notice_date")
    private LocalDateTime createNoticeDate;

    public void edit(EditNoticeRequest editNoticeRequest, String fileUrl) {
        this.title = editNoticeRequest.getTitle();
        this.content = editNoticeRequest.getContent();
        this.imageUrl = fileUrl;
    }
}
