package yh.simplejwt.restjwt.advice.exception;

public class CustomNotOwnerException extends RuntimeException {
    public CustomNotOwnerException(String msg, Throwable t) {
        super(msg, t);
    }
    public CustomNotOwnerException(String msg) {
        super(msg);
    }
    public CustomNotOwnerException() {
        super();
    }
}