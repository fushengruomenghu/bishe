package com.hyl.bishe.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user=request.getSession().getAttribute("Users");

        if (user != null) {
            return true;
        }else {
            request.setAttribute("login_error","请登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
    }
}
