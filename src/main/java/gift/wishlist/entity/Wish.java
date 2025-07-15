package gift.wishlist.entity;

import gift.wishlist.vo.Amount;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlist")
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Embedded
    private Amount amount;

    public Wish() {}

    public Wish(Long id, Long memberId, Long productId, Amount amount) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getProductId() {
        return productId;
    }

    public Amount getAmount() {
        return amount;
    }

    public void changeAmount(Amount amount) {
        this.amount = amount;
    }
}
