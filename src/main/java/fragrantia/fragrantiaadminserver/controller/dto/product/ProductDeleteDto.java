package fragrantia.fragrantiaadminserver.controller.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProductDeleteDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDeleteRequest {
        private List<Long> ids;
    }
}
