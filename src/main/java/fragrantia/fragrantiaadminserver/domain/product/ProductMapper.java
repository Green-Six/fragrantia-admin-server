package fragrantia.fragrantiaadminserver.domain.product;

import fragrantia.fragrantiaadminserver.controller.dto.product.GetProductsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int create(Product product);

    Product getProduct(Long productId);

    List<GetProductsDto> getProductsWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    int getTotalProductCount();

    void updateProduct(Product product);

    void deleteProduct(Product product);
}
