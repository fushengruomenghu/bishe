package com.hyl.bishe.Configurer;

import com.hyl.bishe.interceptor.AdminHandlerInterceptor;
import com.hyl.bishe.interceptor.LoginHandlerInterceptor;
import com.hyl.bishe.interceptor.UserHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")//添加不被拦截的动作
        .excludePathPatterns("/",
                "/doLogin", "/forgetpassword","/register","/doRegister","/forgetPassword", "/checkVerify","/getVerify","/css/**", "/js/**", "/images/**","/font/**");
        registry.addInterceptor(new AdminHandlerInterceptor()).addPathPatterns("/UserInfo","/EditUser");
        registry.addInterceptor(new UserHandlerInterceptor()).addPathPatterns("/Recommend");
    }


}
