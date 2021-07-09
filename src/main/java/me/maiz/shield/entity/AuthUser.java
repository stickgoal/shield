package me.maiz.shield.entity;

import java.util.List;

public interface AuthUser<ID> {

    ID getUserId();

    String getUsername();

    String getPassword();

    /**
     * 返回所有权限，便于前端直接判断
     * @return
     */
    List<Permission> getPermissions();

}
