//package com.fwzx.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private AuthInterceptor addPathPatterns;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 排除 swagger 访问的路径配置
//        String[] swaggerExcludes = new String[]{
//                "/swagger-ui/**",
//                "/swagger-resources/**",
//                "/webjars/**",
//                "/v3/**",
//                "/doc.html",
//        };
//        // 添加拦截器，配置拦截地址
//        registry.addInterceptor(addPathPatterns)
//                .addPathPatterns("/**")
//                .excludePathPatterns(swaggerExcludes);
//    }
//}