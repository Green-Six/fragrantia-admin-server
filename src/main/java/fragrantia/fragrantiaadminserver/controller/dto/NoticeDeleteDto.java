package fragrantia.fragrantiaadminserver.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class NoticeDeleteDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NoticeDeleteRequest {
        private List<Long> ids;
    }
}
