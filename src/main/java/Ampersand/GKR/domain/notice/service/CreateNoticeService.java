package Ampersand.GKR.domain.notice.service;

import Ampersand.GKR.domain.file.service.FileUploadService;
import Ampersand.GKR.domain.notice.entity.Notice;
import Ampersand.GKR.domain.notice.presentation.dto.request.CreateNoticeRequest;
import Ampersand.GKR.domain.notice.repository.NoticeRepository;
import Ampersand.GKR.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RollbackService
@RequiredArgsConstructor
public class CreateNoticeService {

    private final NoticeRepository noticeRepository;

    private final FileUploadService fileUploadService;

    public void execute(CreateNoticeRequest createNoticeRequest, @Nullable MultipartFile file) {

        String fileUrl = (file != null) ? fileUploadService.execute(file).getImageUrl() : null;

        Notice notice = Notice.builder()
                .title(createNoticeRequest.getTitle())
                .content(createNoticeRequest.getContent())
                .imageUrl(fileUrl)
                .createNoticeDate(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);
    }
}
