package fragrantia.fragrantiaadminserver.controller.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductUpdateDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductUpdateRequest {
        private String name;
        private Long price;
        private String category;
        private String detail;
        private String imgUrl;
        private Long id;
        private Long adminId;
    }
}
