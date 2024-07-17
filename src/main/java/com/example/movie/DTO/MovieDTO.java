package com.example.movie.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MovieDTO {
    private Integer code; //기본키
    private String name;//영화제목
    private String director;//감동
    private String nation; //국가
    private String genre;//장르
    private String actor; //주연
    private String open; //개발원
    private String synopsis; //영화개요
    private String sysname; //포스터 파일이름
}
