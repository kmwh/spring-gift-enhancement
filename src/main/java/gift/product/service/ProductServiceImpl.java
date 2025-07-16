package gift.product.service;

import gift.global.exception.ProductNotFoundException;
import gift.product.dto.ProductRequestDto;
import gift.product.dto.ProductResponseDto;
import gift.product.entity.Product;
import gift.product.repository.ProductRepository;
import gift.product.vo.Name;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = new Product(
            null,
            new Name(requestDto.name()),
            requestDto.price(),
            requestDto.imageUrl());
        product = productRepository.save(product);
        return ProductResponseDto.from(product);
    }

    @Override
    public List<ProductResponseDto> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
            .map(ProductResponseDto::from)
            .toList();
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.orElseThrow(ProductNotFoundException::new);

        return ProductResponseDto.from(product);
    }

    @Override
    public void updateProduct(Long id, ProductRequestDto requestDto) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }

        Product product = new Product(
            id,
            new Name(requestDto.name()),
            requestDto.price(),
            requestDto.imageUrl()
        );
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }

        productRepository.deleteById(id);
    }
}
