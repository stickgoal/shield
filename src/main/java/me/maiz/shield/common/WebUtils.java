package me.maiz.shield.common;

import me.maiz.shield.common.Result;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

   public static void printResult(ServletResponse response, Result result) throws IOException {
        String json = "{\"success\":\""+result.isSuccess()+"\",\"code\":\"" + result.getCode() + "\",\"message\":\"" + result.getMessage() + "\"}";
        response.getWriter().print(json);
    }
}
