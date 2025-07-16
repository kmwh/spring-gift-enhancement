package gift.product.service;

import gift.product.dto.ProductRequestDto;
import gift.product.dto.ProductResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto requestDto);
    List<ProductResponseDto> findAllProducts(Pageable pageable);
    ProductResponseDto findProductById(Long id);
    void updateProduct(Long id, ProductRequestDto requestDto);
    void deleteProduct(Long id);
}
