package fragrantia.fragrantiaadminserver.controller;

import fragrantia.fragrantiaadminserver.controller.dto.GetNoticesDto;
import fragrantia.fragrantiaadminserver.controller.dto.NoticeCreateDto.NoticeCreateRequest;
import fragrantia.fragrantiaadminserver.domain.notice.Notice;
import fragrantia.fragrantiaadminserver.domain.notice.service.NoticeService;
import fragrantia.fragrantiaadminserver.security.DefaultFragrantiaAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public String getNotice(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size) {
        int offset = page * size;
        List<GetNoticesDto> notices = noticeService.getNoticesWithPaging(offset, size);
        int totalCount = noticeService.getTotalNoticeCount();

        model.addAttribute("notices", notices);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", calculateTotalPages(totalCount, size));

        return "page/notice";
    }

    private int calculateTotalPages(int totalCount, int size) {
        return (int) Math.ceil((double) totalCount / size);
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
