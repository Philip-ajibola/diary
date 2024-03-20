package exception;

public class UserNotFoundException extends DiaryException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
