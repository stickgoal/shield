package me.maiz.shield.service;

import me.maiz.shield.entity.AuthUser;
import me.maiz.shield.entity.LoginParam;

import java.util.Date;
import java.util.Map;

/**
 * 安全服务,支持在前后端分离情况下，以token为基础的登录及授权判断
 */
public interface ShieldService {

    /**
     * 登录
     * @param authParam
     * @return
     */
    AuthUser login(LoginParam authParam);


    /**
     * 签发token，无状态登录时通过token识别用户，该方法提供token
     * @param authUser
     * @return
     */
    String issueToken(AuthUser authUser, Date expire, Map<String,Object> attachments);


    /**
     * 登出
     * @param token
     */
    void logout(String token);


    void checkValid(String authToken,String url);

//    /**
//     * 检查登录状态
//     * @param authToken
//     * @return
//     */
//    void checkLogin(String authToken);
//
//    /**
//     * 是否已授权
//     * @param authToken
//     * @param permission
//     * @return
//     */
//    boolean isAuthorized(String authToken,String permission);

}
