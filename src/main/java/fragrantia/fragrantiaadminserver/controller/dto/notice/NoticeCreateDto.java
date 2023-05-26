package fragrantia.fragrantiaadminserver.controller.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoticeCreateDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeCreateRequest {
        private String title;
        private String content;
    }
}
