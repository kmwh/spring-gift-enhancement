package gift.product.service;

import gift.global.dto.PageResponseDto;
import gift.product.dto.ProductRequestDto;
import gift.product.dto.ProductResponseDto;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDto create(ProductRequestDto requestDto);
    PageResponseDto<ProductResponseDto> findAll(Pageable pageable);
    ProductResponseDto findById(Long id);
    ProductResponseDto update(Long id, ProductRequestDto requestDto);
    void delete(Long id);
}
