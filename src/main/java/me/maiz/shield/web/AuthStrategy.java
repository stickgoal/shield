package me.maiz.shield.web;

public interface AuthStrategy {

    boolean isLoginOrAnonymous(String url);

    void tokenNotPresent();

    void authzFail(String name, String message);
}
