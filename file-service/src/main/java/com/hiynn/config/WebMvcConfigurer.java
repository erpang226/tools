package com.hiynn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    @Value("${file.uploadUrl}")
    private String uploadUrl;

    @Value("${file.templateUrl}")
    private String templateUrl;

    @Value("${file.templateStaticPath}")
    private String templateStaticPath;

    @Value("${file.uploadStaticPath}")
    private String uploadStaticPath;

    @Value("${file.resourceUrl}")
    private String resourceUrl;

    @Value("${file.resourceStaticPath}")
    private String resourceStaticPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件资源目录请求路径
        registry.addResourceHandler(templateStaticPath).addResourceLocations("file:".concat(templateUrl));
        registry.addResourceHandler(uploadStaticPath).addResourceLocations("file:".concat(uploadUrl));
        registry.addResourceHandler(resourceStaticPath).addResourceLocations("file:".concat(resourceUrl));


        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
