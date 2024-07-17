package com.example.movie.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//resources에 외부폴더를 추가 등록
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}")//application.p
    String uploadPath;

    @Override  //기존 메소드를 새로운 내용으로 덮어쓰기
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(uploadPath);
    }
    //html에서 /upload/ 요청하면 c:/movie 폴더로 연결
}
