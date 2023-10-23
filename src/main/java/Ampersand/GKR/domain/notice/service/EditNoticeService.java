package Ampersand.GKR.domain.notice.service;

import Ampersand.GKR.domain.file.service.FileUploadService;
import Ampersand.GKR.domain.notice.entity.Notice;
import Ampersand.GKR.domain.notice.presentation.dto.request.EditNoticeRequest;
import Ampersand.GKR.global.annotation.RollbackService;
import Ampersand.GKR.global.util.NoticeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

@RollbackService
@RequiredArgsConstructor
public class EditNoticeService {

    private final NoticeUtil noticeUtil;

    private final FileUploadService fileUploadService;

    public void execute(Long id, EditNoticeRequest editNoticeRequest, @Nullable MultipartFile file) {

        Notice notice = noticeUtil.findNoticeById(id);

        String fileUrl = (file != null) ? fileUploadService.execute(file).getImageUrl() : notice.getImageUrl();

        notice.edit(editNoticeRequest, fileUrl);
    }
}
