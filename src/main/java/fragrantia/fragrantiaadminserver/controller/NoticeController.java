package fragrantia.fragrantiaadminserver.controller;

import fragrantia.fragrantiaadminserver.controller.dto.NoticeCreateDto.NoticeCreateRequest;
import fragrantia.fragrantiaadminserver.domain.notice.service.NoticeService;
import fragrantia.fragrantiaadminserver.security.DefaultFragrantiaAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public String getNotice() {
        return "page/notice";
    }

    @PostMapping
    @ResponseBody
    public void create(
        @AuthenticationPrincipal DefaultFragrantiaAdmin admin,
        @RequestBody NoticeCreateRequest req
    ) {
        noticeService.create(admin.getId(), req.getTitle(), req.getContent());
    }
}
