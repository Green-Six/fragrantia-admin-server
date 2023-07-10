package fragrantia.fragrantiaadminserver.domain.product;

import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;

import static fragrantia.fragrantiaadminserver.utils.Preconditions.require;

@Getter
public class Product {
    private Long id;
    private Long adminId;
    private String name;
    private Long price;
    private String category;
    private String detail;
    private String imgUrl;
    private Long view = 0L;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Product(Long id, Long adminId, String name, Long price, String category, String detail, String imgUrl, Long view, LocalDateTime createdAt) {
        this.id = id;
        this.adminId = adminId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.detail = detail;
        this.imgUrl = imgUrl;
        this.view = view;
        this.createdAt = createdAt;
    }

    private Product(Long adminId, String name, Long price, String category, String detail, String imgUrl) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.detail = detail;
        this.imgUrl = imgUrl;
        this.adminId = adminId;
    }

    public static Product create(Long adminId, String name, Long price, String category, String detail, String imgUrl) {
        require(Strings.isNotBlank(name));
        require(price != null);
        require(Strings.isNotBlank(category));
        require(Strings.isNotBlank(detail));

        return new Product(adminId, name, price, category, detail, imgUrl);
    }

    public void update(Long adminId, String name, Long price, String category, String detail, String imgUrl) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.detail = detail;
        this.imgUrl = imgUrl;
        this.adminId = adminId;
    }
}
