package fragrantia.fragrantiaadminserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "page/login";
    }

    @GetMapping("/join")
    public String join() {
        return "page/join";
    }

    @GetMapping("/find-account")
    public String findAccount() {
        return "page/findAccount";
    }
}