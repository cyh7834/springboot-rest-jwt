package yh.simplejwt.restjwt.advice.exception;

public class CustomLoginFailedException extends RuntimeException{
    public CustomLoginFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomLoginFailedException(String msg) {
        super(msg);
    }

    public CustomLoginFailedException() {
        super();
    }
}
