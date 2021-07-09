package me.maiz.shield.token;

import me.maiz.shield.entity.AuthUser;

import java.util.Date;
import java.util.Map;

public interface TokenManager {
    /**
     * 签发一个token
     * @param user
     * @param expire
     * @param attachments
     * @return
     */
    String issue(AuthUser user, Date expire, Map<String,Object> attachments);

    /**
     * 验证token合法性
     * @param token
     */
    void validate(String token);

    /**
     * 使token过期，此方法依赖于具体实现
     * @param token
     */
    void expire(String token);

    /**
     * 解析token
     * @param token
     * @return
     */
    Map<String,Object> parse(String token);
}
