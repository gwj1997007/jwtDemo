package com.example.jwt_demo.config;

import com.example.jwt_demo.Intercepter.JwtIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description: java类作用描述
 * @Author: gwj
 * @CreateDate: 2020/12/7 14:17
 * @UpdateUser: gwj
 * @UpdateDate: 2020/12/7 14:17
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
public class JwtIntecepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/logout")
                .excludePathPatterns("/user/unAuth");

    }
}
