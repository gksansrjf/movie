package com.example.movie.Entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code; //기본키
    @Column(length = 100,nullable = false)
    private String name;//영화제목
    @Column(length = 20)
    private String director;//감동
    @Column(length = 40)
    private String nation; //국가
    @Column(length = 40)
    private String genre;//장르
    @Column(length = 20)
    private String actor; //주연
    private String open; //개발원
    private String synopsis; //영화개요
    private String sysname; //포스터 파일이름

}
