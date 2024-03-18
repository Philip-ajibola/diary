package exception;

public class DiaryNotFound extends RuntimeException {
    public DiaryNotFound(String message){
        super(message);
    }
}
