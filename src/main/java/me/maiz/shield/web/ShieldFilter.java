package me.maiz.shield.web;

import me.maiz.shield.common.Result;
import me.maiz.shield.common.WebUtils;
import me.maiz.shield.exceptions.AuthcException;
import me.maiz.shield.exceptions.AuthzException;
import me.maiz.shield.exceptions.ShieldException;
import me.maiz.shield.service.ShieldService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ShieldFilter implements Filter {

    private ShieldService shieldService;

    private AuthStrategy authStrategy;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //0. 是否为登录请求或其他放行请求
        HttpServletRequest req= (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        if(authStrategy.isLoginOrAnonymous(requestURI)){
            chain.doFilter(request,response);
            return;
        }

        //1.检查是否登录及授权
        String token = req.getHeader("Authorization");
        if(token==null){
            authStrategy.tokenNotPresent();
            return;
        }

        try {
            shieldService.checkValid(token, requestURI);
        }catch (AuthcException e){
            //TODO logging exception
            WebUtils.printResult(response,new Result(false,"0100","未登录，请先登录"));
        }catch (AuthzException e){
            //TODO logging exception
            authStrategy.authzFail(e.getClass().getName(),e.getMessage());

        }catch (Exception e){
            //TODO logging exception
            WebUtils.printResult(response,new Result(false,"0200","授权检查失败，请重试"));
        }


    }

    @Override
    public void destroy() {

    }

    public void setShieldService(ShieldService shieldService) {
        this.shieldService = shieldService;
    }

    public void setAuthStrategy(AuthStrategy authStrategy) {
        this.authStrategy = authStrategy;
    }
}
