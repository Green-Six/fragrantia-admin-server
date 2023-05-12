package fragrantia.fragrantiaadminserver.controller;

import fragrantia.fragrantiaadminserver.controller.dto.AdminCreateDto.AdminCreateRequest;
import fragrantia.fragrantiaadminserver.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor // 생성자 생성해주는 코드
public class AdminController {

    private final AdminService adminService;

    @PostMapping // Post 방식으로 Mapping 하겠다는 의미.
    @ResponseBody // 뷰를 body에 넣지 않고 그대로 브라우저에 띄우겠다는 의미.
    public void create(
        @RequestBody AdminCreateRequest req //
    ) {
        adminService.create(req.getEmail(), req.getPassword(), req.getName(), req.getBranch());
    }
}
