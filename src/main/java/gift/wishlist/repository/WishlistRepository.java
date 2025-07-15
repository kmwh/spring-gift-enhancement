package gift.wishlist.repository;

import gift.wishlist.entity.Wish;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wish, Long> {
    List<Wish> findAllByMemberId(Long memberId);

//    List<Wish> findAllByMemberId(Long memberId);
//    long save(Wish wish);
//    void update(Long id, Amount amount);
//    void delete(Long memberId, Long productId);
}
