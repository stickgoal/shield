package me.maiz.shield.token;

import java.util.Date;
import java.util.Map;

public interface AuthToken {

    String getToken();

    Date getExpires();

    Map<String,Object> getPayLoad();

}
