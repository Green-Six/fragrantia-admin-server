package fragrantia.fragrantiaadminserver.controller.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductsDto {
    private Long id;
    private Long adminId;
    private String name;
    private Long price;
    private String category;
    private String detail;
    private String imgUrl;
    private String adminName;
    private Long view;
    private LocalDateTime createdAt;
}
