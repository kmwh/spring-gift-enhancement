package gift.wishlist.repository;

import gift.wishlist.entity.Wish;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wish, Long> {
    List<Wish> findAllByMemberId(Long memberId);
}
