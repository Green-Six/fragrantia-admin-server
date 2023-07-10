package fragrantia.fragrantiaadminserver.domain.product.service;

import fragrantia.fragrantiaadminserver.controller.dto.product.GetProductsDto;
import fragrantia.fragrantiaadminserver.domain.product.Product;

import java.util.List;

public interface ProductService {

    Product create(Long adminId, String name, Long price, String category, String detail, String imgUrl);

    List<GetProductsDto> getProductsWithPaging(int offset, int limit);

    int getTotalProductCount();

    void updateProduct(Long adminId, String name, Long price, String category, String detail, String file, Long id);

    void deleteProduct(Long Id);
}
