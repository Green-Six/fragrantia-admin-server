package fragrantia.fragrantiaadminserver.controller;

import fragrantia.fragrantiaadminserver.controller.dto.admin.AdminCreateDto.AdminCreateRequest;
import fragrantia.fragrantiaadminserver.controller.dto.admin.MailDto;
import fragrantia.fragrantiaadminserver.domain.admin.service.AdminService;
import fragrantia.fragrantiaadminserver.domain.admin.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor // 생성자 생성해주는 코드
public class AdminController {

    private final AdminService adminService;
    private final SendEmailService sendEmailService;

    @PostMapping // Post 방식으로 Mapping 하겠다는 의미.
    @ResponseBody // 뷰를 body에 넣지 않고 그대로 브라우저에 띄우겠다는 의미.
    public void create(
        @RequestBody AdminCreateRequest req //
    ) {
        adminService.create(req.getEmail(), req.getPassword(), req.getName(), req.getBranch());
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public int checkEmailDuplication(@RequestParam("email") String email) {
        return adminService.isEmailDuplicate(email);
    }

    @GetMapping("/findPw")
    public @ResponseBody Map<String, Boolean> pw_find(String name, String email, String branch){
        Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = sendEmailService.emailCheck(name, email, branch);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);
        return json;
    }

    @PostMapping("/findPw/sendEmail")
    public @ResponseBody void sendEmail(String name, String email, String branch){
        MailDto dto = sendEmailService.createMailAndChangePassword(name, email, branch);
        sendEmailService.mailSend(dto);
    }

}
