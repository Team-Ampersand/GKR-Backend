package Ampersand.GKR.domain.notice.presentation;

import Ampersand.GKR.domain.notice.presentation.dto.request.CreateNoticeRequest;
import Ampersand.GKR.domain.notice.presentation.dto.request.EditNoticeRequest;
import Ampersand.GKR.domain.notice.presentation.dto.response.DetailNoticeResponse;
import Ampersand.GKR.domain.notice.presentation.dto.response.ListNoticeResponse;
import Ampersand.GKR.domain.notice.service.*;
import Ampersand.GKR.global.annotation.RestRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestRequestService("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final EditNoticeService editNoticeService;

    private final ListNoticeService listNoticeService;

    private final CreateNoticeService createNoticeService;

    private final DeleteNoticeService deleteNoticeService;

    private final DetailNoticeService detailNoticeService;


    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestPart(name = "notice") CreateNoticeRequest createNoticeRequest, @RequestPart(name = "file", required = false) MultipartFile file) {
        createNoticeService.execute(createNoticeRequest, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListNoticeResponse> list() {
        var list = listNoticeService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailNoticeResponse> detail(@PathVariable Long id) {
        DetailNoticeResponse detailNoticeResponse = detailNoticeService.execute(id);
        return new ResponseEntity<>(detailNoticeResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteNoticeService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestPart(name = "notice") EditNoticeRequest editNoticeRequest, @RequestPart(name = "file", required = false) MultipartFile file) {
        editNoticeService.execute(id, editNoticeRequest, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
