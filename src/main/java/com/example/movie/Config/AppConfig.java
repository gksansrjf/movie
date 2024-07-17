package com.example.movie.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//기본에 메소드를 변경
@Configuration
public class AppConfig {
    @Bean //메소드에 bean으로 선언해서 메소드 등록
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
