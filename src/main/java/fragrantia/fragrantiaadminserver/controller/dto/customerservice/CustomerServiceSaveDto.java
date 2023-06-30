package fragrantia.fragrantiaadminserver.controller.dto.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CustomerServiceSaveDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomerServiceSaveRequest {
        private String userName;
        private String email;
        private String answer;
    }
}
