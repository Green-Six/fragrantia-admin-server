package fragrantia.fragrantiaadminserver.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoticeUpdateDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeUpdateRequest {
        private String title;
        private String content;
        private Long id;
    }
}
