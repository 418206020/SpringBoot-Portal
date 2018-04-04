package com.micro.boot.app.config;

import com.micro.boot.app.resolver.LoginUserHandlerMethodArgumentResolver;
import com.micro.boot.interceptor.AuthorizationInterceptor;
import com.micro.boot.interceptor.MobileTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * MVC配置
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-04-20 22:30
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private MobileTokenInterceptor mobileTokenInterceptor;
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //mvc注入拦截器
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/app/**");
        registry.addInterceptor(mobileTokenInterceptor).addPathPatterns("/app/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }
}