package fragrantia.fragrantiaadminserver.domain.product.service;

import fragrantia.fragrantiaadminserver.controller.dto.product.GetProductsDto;
import fragrantia.fragrantiaadminserver.domain.product.Product;
import fragrantia.fragrantiaadminserver.domain.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DomainProductService implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public Product create(Long adminId, String name, Long price, String category, String detail, String imgUrl) {
        Product product = Product.create(adminId, name, price, category, detail, imgUrl);

        productMapper.create(product);

        return product;
    }

    @Override
    public List<GetProductsDto> getProductsWithPaging(int offset, int limit) {
        return productMapper.getProductsWithPaging(offset, limit);
    }

    @Override
    public int getTotalProductCount() {
        return productMapper.getTotalProductCount();
    }

    @Override
    public void updateProduct(Long adminId, String name, Long price, String category, String detail, String file, Long id) {
        Product product = productMapper.getProduct(id);

        product.update(adminId, name, price, category, detail, file);

        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productMapper.getProduct(id);

        productMapper.deleteProduct(product);
    }
}
