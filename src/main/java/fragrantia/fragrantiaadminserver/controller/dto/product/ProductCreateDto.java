package fragrantia.fragrantiaadminserver.controller.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductCreateDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductCreateRequest {
        private String name;
        private Long price;
        private String category;
        private String detail;
        private String imgUrl;
    }
}
