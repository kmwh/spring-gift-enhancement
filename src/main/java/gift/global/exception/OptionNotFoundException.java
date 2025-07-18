package gift.global.exception;

public class OptionNotFoundException extends RuntimeException {

    public OptionNotFoundException() {
        super("해당 옵션을 찾을 수 없습니다.");
    }
}
