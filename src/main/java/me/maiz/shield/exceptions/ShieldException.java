package me.maiz.shield.exceptions;

public class ShieldException extends RuntimeException{
    public ShieldException() {
    }

    public ShieldException(String message) {
        super(message);
    }

    public ShieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShieldException(Throwable cause) {
        super(cause);
    }

    public ShieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
