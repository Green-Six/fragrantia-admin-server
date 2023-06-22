package fragrantia.fragrantiaadminserver.domain.admin.service;

import fragrantia.fragrantiaadminserver.controller.dto.admin.MailDto;
import fragrantia.fragrantiaadminserver.domain.admin.Admin;
import fragrantia.fragrantiaadminserver.domain.admin.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SendEmailService {

    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "dlwjddlr97@gmail.com";

    public MailDto createMailAndChangePassword(String name, String email, String branch) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(email);
        dto.setTitle(name + "님의 Fragrantia 임시 비밀번호 안내 이메일입니다.");
        dto.setMessage("안녕하세요, Fragrantia 임시 비밀번호 안내 관련 이메일입니다."
            + "[" + branch + "]점 " + "[" + name + "]님의 임시 비밀번호는" + str + "입니다.");
        updatePassword(str, email);
        return dto;
    }

    private void updatePassword(String str, String email) {
        String pw = passwordEncoder.encode(str);
        String id = adminMapper.getByEmail(email).getEmail();
        updateAdminPassword(id, pw);
    }

    private String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void updateAdminPassword(String email, String password) {
        Admin admin = adminMapper.getByEmail(email);

        admin.update(email, password);

        adminMapper.updateAdminPassword(admin);
    }

    public boolean emailCheck(String name, String email, String branch) {
        Admin admin = adminMapper.getByEmail(email);
        return admin != null && admin.getName().equals(name) && admin.getBranch().equals(branch);
    }

    public void mailSend(MailDto mailDto){
        System.out.println("이메일 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }
}
