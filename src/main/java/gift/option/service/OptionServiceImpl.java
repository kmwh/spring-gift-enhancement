package gift.option.service;

import gift.global.exception.OptionNotFoundException;
import gift.global.exception.ProductNotFoundException;
import gift.option.dto.OptionRequestDto;
import gift.option.dto.OptionResponseDto;
import gift.option.entity.Option;
import gift.option.repository.OptionRepository;
import gift.product.entity.Product;
import gift.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService{
    private final OptionRepository optionRepository;
    private final ProductRepository productRepository;

    public OptionServiceImpl(OptionRepository optionRepository, ProductRepository productRepository) {
        this.optionRepository = optionRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OptionResponseDto create(Long productId, OptionRequestDto requestDto) {
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.orElseThrow(ProductNotFoundException::new);

        Option option = new Option(
            requestDto.name(),
            requestDto.quantity()
        );
        option.setProduct(product);

        option = optionRepository.save(option);
        return OptionResponseDto.from(option);
    }

    @Override
    public List<OptionResponseDto> findAllByProductId(Long productId) {
        return optionRepository.findAllByProductId(productId)
            .stream()
            .map(OptionResponseDto::from)
            .toList();
    }

    @Override
    public OptionResponseDto findByProductIdAndOptionId(Long productId, Long optionId) {
        Optional<Option> optionOptional = optionRepository.findByIdAndProductId(productId, optionId);
        Option option = optionOptional.orElseThrow(OptionNotFoundException::new);

        return OptionResponseDto.from(option);
    }

    @Override
    public OptionResponseDto update(Long productId, Long optionId, OptionRequestDto requestDto) {
        Optional<Option> optionOptional = optionRepository.findByIdAndProductId(productId, optionId);
        Option option = optionOptional.orElseThrow(OptionNotFoundException::new);

        option.changeName(requestDto.name());
        option.changeQuantity(requestDto.quantity());
        return OptionResponseDto.from(option);
    }

    @Override
    public void delete(Long productId, Long optionId) {
        if (!optionRepository.existsByIdAndProductId(productId, optionId)) {
            throw new OptionNotFoundException();
        }

        optionRepository.deleteById(optionId);
    }
}
