package Ampersand.GKR.domain.notice.presentation;

import Ampersand.GKR.domain.notice.presentation.dto.request.CreateNoticeRequest;
import Ampersand.GKR.domain.notice.presentation.dto.request.EditNoticeRequest;
import Ampersand.GKR.domain.notice.service.CreateNoticeService;
import Ampersand.GKR.domain.notice.service.DeleteNoticeService;
import Ampersand.GKR.domain.notice.service.EditNoticeService;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestRequestService("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final CreateNoticeService createNoticeService;

    private final DeleteNoticeService deleteNoticeService;

    private final EditNoticeService editNoticeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestPart(name = "notice") CreateNoticeRequest createNoticeRequest, @RequestPart(name = "file") MultipartFile file) {
        createNoticeService.execute(createNoticeRequest, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteNoticeService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestPart(name = "notice") EditNoticeRequest editNoticeRequest, @RequestPart(name = "file") MultipartFile file) {
        editNoticeService.execute(id, editNoticeRequest, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
