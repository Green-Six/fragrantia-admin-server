package fragrantia.fragrantiaadminserver.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AdminCreateDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminCreateRequest {
        private String email;
        private String password;
        private String name;
        private String branch;
    }
}
