package gift.wishlist.service;

import gift.global.exception.WishlistNotFoundException;
import gift.wishlist.dto.CreateWishRequestDto;
import gift.wishlist.dto.UpdateWishRequestDto;
import gift.wishlist.dto.WishResponseDto;
import gift.wishlist.entity.Wish;
import gift.wishlist.repository.WishlistRepository;
import gift.wishlist.vo.Amount;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService{
    private final WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public List<WishResponseDto> findAllByMemberId(Long memberId, Pageable pageable) {
        return wishlistRepository.findAllByMemberId(memberId, pageable)
            .map(WishResponseDto::from)
            .toList();
    }

    @Override
    public WishResponseDto create(Long memberId, CreateWishRequestDto requestDto) {
        Wish wish = new Wish(
            null,
            memberId,
            requestDto.productId(),
            new Amount(requestDto.amount())
        );
        wish = wishlistRepository.save(wish);

        return WishResponseDto.from(wish);
    }

    @Override
    public void update(Long id, UpdateWishRequestDto requestDto) {
        Wish wish = wishlistRepository.findById(id)
            .orElseThrow(WishlistNotFoundException::new);

        wish.changeAmount(new Amount(requestDto.amount())); // JPA가 dirty checking 으로 변경 감지하여 UPDATE 실행
    }

    @Override
    public void delete(Long id) {
        if (!wishlistRepository.existsById(id)) {
            throw new WishlistNotFoundException();
        }

        wishlistRepository.deleteById(id);
    }
}
