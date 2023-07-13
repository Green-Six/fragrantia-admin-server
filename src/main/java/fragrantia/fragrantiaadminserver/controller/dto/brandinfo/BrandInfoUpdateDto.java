package fragrantia.fragrantiaadminserver.controller.dto.brandinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BrandInfoUpdateDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandInfoUpdateRequest {
        private String historyImgUrl;
        private String historyDetail;
        private String natureImgUrl;
        private String natureDetail;
        private String humanImgUrl;
        private String humanDetail;
    }
}
