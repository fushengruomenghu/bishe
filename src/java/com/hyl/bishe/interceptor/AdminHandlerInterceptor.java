package com.hyl.bishe.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminHandlerInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object Usersrole=request.getSession().getAttribute("Usersrole");
        if (Usersrole.toString().equals("1")) {
            return true;
        }else {
            request.getRequestDispatcher("/schoolInfo").forward(request,response);
            return false;
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
