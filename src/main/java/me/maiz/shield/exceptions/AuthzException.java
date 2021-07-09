package me.maiz.shield.exceptions;

public class AuthzException extends ShieldException{
    public AuthzException() {
    }

    public AuthzException(String message) {
        super(message);
    }

    public AuthzException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthzException(Throwable cause) {
        super(cause);
    }

    public AuthzException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
