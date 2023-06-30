package fragrantia.fragrantiaadminserver.controller.dto.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CustomerServiceUpdateDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomerServiceUpdateRequest {
        private String answer;
        private Long id;
    }
}
