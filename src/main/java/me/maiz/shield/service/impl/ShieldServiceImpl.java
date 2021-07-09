package me.maiz.shield.service.impl;


import me.maiz.shield.entity.AuthUser;
import me.maiz.shield.entity.LoginParam;
import me.maiz.shield.entity.Permission;
import me.maiz.shield.exceptions.UnauthorizedException;
import me.maiz.shield.service.ShieldService;
import me.maiz.shield.token.TokenManager;
import me.maiz.shield.exceptions.LoginException;
import me.maiz.shield.realm.ShieldRealm;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShieldServiceImpl implements ShieldService {

    private ShieldRealm realm;

    private TokenManager tokenManager;

    @Override
    public AuthUser login(LoginParam authParam) {

        AuthUser user = realm.getUser(authParam.getUsername());
        if (user == null) {
            throw new LoginException("用户名或者密码不正确");
        }
        if (!authParam.getPassword().equals(user.getPassword())) {
            throw new LoginException("用户名或者密码不正确");
        }

        return user;
    }

    @Override
    public String issueToken(AuthUser authUser, Date expire, Map<String, Object> attachments) {

        return tokenManager.issue(authUser, expire, attachments);

    }

    @Override
    public void logout(String authToken) {
        tokenManager.expire(authToken);
    }

    @Override
    public void checkValid(String authToken,String url) {
        Map<String, Object> payload = null;

        try {
            payload = tokenManager.parse(authToken);
        }catch (Exception e){
            throw new LoginException("您未登录或已过期，请先登录");
        }

        String permission = realm.urlToPermission(url);
        if(permission==null){
            //该url未配置权限，直接放行
            return;
        }
        List<Permission> permissions = realm.getPermissions(payload.get("userId"));

        if(!permissions.contains(permission)) {
            throw new UnauthorizedException("您无权访问该资源,请检查是否拥有["+permission+"]权限");
        }
    }

//    @Override
//    public void checkLogin(String authToken) {
//
//
//
//    }
//
//    @Override
//    public boolean isAuthorized(String authToken, String permission) {
//
//        Map<String, Object> payload = tokenManager.parse(authToken);
//
//        //TODO cache
//        List<Permission> permissions = realm.getPermissions(payload.get("userId"));
//
//        return permissions.contains(permission);
//    }

    public void setRealm(ShieldRealm realm) {
        this.realm = realm;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
}
