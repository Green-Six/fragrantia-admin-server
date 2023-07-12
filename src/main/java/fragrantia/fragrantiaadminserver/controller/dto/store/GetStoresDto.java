package fragrantia.fragrantiaadminserver.controller.dto.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetStoresDto {
    private Long id;
    private Double latitude;
    private Double longitude;
    private Integer zip;
    private String address;
    private String name;
    private String detail;
    private String telephone;
    private String imgUrl;
}
