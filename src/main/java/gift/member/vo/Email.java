package gift.member.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Email {
    @Column(name = "email", nullable = false)
    private String email;

    protected Email() {}

    public Email(String value) {
        check(value);
        this.email = value;
    }

    private void check(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
        }
    }

    public String getValue() {
        return email;
    }
}
