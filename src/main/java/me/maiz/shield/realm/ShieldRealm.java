package me.maiz.shield.realm;

import com.sun.xml.internal.bind.v2.model.core.ID;
import me.maiz.shield.entity.AuthUser;
import me.maiz.shield.entity.Permission;

import java.util.List;

public interface ShieldRealm<ID> {
    /**
     * 获取到用户
     * @param username
     * @return
     */
   AuthUser<ID> getUser(String username);

    /**
     * 获取url对应的权限
     * @param url
     * @return
     */
    String urlToPermission(String url);

    /**
     * 根据用户ID查询权限列表
     * @param userId
     * @return
     */
    List<Permission> getPermissions(ID userId);

}
