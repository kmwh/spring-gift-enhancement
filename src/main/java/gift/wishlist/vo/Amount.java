package gift.wishlist.vo;

import gift.global.exception.InValidAmountException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Amount {
    @Column(nullable = false)
    private Integer amount;

    public Amount() {}

    public Amount(int value) {
        check(value);
        this.amount = value;
    }

    private void check(Integer amount) {
        if (amount == null || amount > 99) {
            throw new InValidAmountException();
        }
    }

    public Integer getValue() {
        return amount;
    }
}
