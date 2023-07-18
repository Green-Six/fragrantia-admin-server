package fragrantia.fragrantiaadminserver.domain.customerservice;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerService {
    private Long id;
    private Long adminId;
    private String title;
    private String category;
    private String userName;
    private String email;
    private String detail;
    private String answer;
    private String imgUrl;
    private LocalDateTime createdAt = LocalDateTime.now();

    public CustomerService(Long id, Long adminId, String title, String category, String userName, String email, String detail, String answer, String imgUrl, LocalDateTime createdAt) {
        this.id = id;
        this.adminId = adminId;
        this.title = title;
        this.category = category;
        this.userName = userName;
        this.email = email;
        this.detail = detail;
        this.answer = answer;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
    }

    public void update(String answer) {
        this.answer = answer;
    }
}
