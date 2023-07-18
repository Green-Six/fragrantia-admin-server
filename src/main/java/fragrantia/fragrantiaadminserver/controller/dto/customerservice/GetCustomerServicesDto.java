package fragrantia.fragrantiaadminserver.controller.dto.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerServicesDto {
    private Long id;
    private Long adminId;
    private String title;
    private String category;
    private String userName;
    private String email;
    private String detail;
    private String answer;
    private String imgUrl;
    private LocalDateTime createdAt;
}
