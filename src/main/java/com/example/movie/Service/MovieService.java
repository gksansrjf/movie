package com.example.movie.Service;





import com.example.movie.DTO.MovieDTO;
import com.example.movie.Entity.MovieEntity;
import com.example.movie.Repository.MovieRepository;
import com.example.movie.Util.FileUpload;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

//내부처리(데이터베이스 , 페이지, 파일업로드)
@Service
//오류가 없으면 데이터베이스 일괄로 작업
@Transactional
@RequiredArgsConstructor
//기로파일(System.out.print를 대체)
@Log
public class MovieService {
    //사용할 클래스를 추가
    public final MovieRepository movieRepository; //데이터베이스 작업
    private final ModelMapper modelMapper; //변환
    private final FileUpload fileUpload ; //파일업로드

    //삽입(write)-메소드명은 html이름과 유사하게
    public void write(MovieDTO movieDTO, MultipartFile poster){
        log.info("서비스에서 저장작업");
        //파일면저 저장하고 ,새로운파일명을 DTO에 저장해서 테이블 작업
        String filename = null;
        if (!poster.isEmpty()){ //!isEmpty(비어있지 않으면 ), 포스터 이미지 파일이 존재하면
            filename = fileUpload.FileUpload(poster); //파일저장후 파일명을 지정
            movieDTO.setSysname(filename);

        }
        log.info("테이블에 영화를 지정");
        MovieEntity movieEntity = modelMapper.map(movieDTO,MovieEntity.class);
        movieRepository.save(movieEntity);
    }
    //수정
    public void update(MovieDTO movieDTO,MultipartFile poster){
        log.info("서비스에서 수정작업");
        MovieEntity movieEntity = movieRepository.findById(movieDTO.getCode()).orElseThrow();

        if (!poster.isEmpty()){
            log.info("기존 파일 삭제 후 새로운 포스터파일을 업로드");
            if (!movieDTO.getSysname().isEmpty()){
                fileUpload.FileDelete(movieDTO.getSysname());
            }


            String filenme = fileUpload.FileUpload(poster);
            movieDTO.setSysname(filenme);
        }
        log.info("테이블에 영화를 수정");
        MovieEntity movie = modelMapper.map(movieDTO,MovieEntity.class);
        movieRepository.save(movie);
    }
    //삭제
    public void delete(Integer code){
        log.info("서비스에서 삭제작업");

        //포스터가 존재하면 파일삭제
        MovieEntity movieEntity = movieRepository.findById(code).orElseThrow();
        if (!movieEntity.getSysname().isEmpty()){
            fileUpload.FileDelete(movieEntity.getSysname());
        }

        movieRepository.deleteById(code);


    }
    //개별조회
    public MovieDTO detail(Integer code){
        log.info("서비스에서 개별조회작업");

        MovieEntity movieEntity = movieRepository.findById(code).orElseThrow();
        MovieDTO movieDTO = modelMapper.map(movieEntity,MovieDTO.class);

        //조회확인
        System.out.println(movieDTO.toString());
        return movieDTO;
    }
    //전체조회
    public List<MovieDTO>list(){
        log.info("서비스에서 전체조회작업");

        List<MovieEntity> movieEntities = movieRepository.findAll();
        List<MovieDTO> movieDTOS = Arrays.asList(modelMapper.map(movieEntities,MovieDTO[].class));

        return  movieDTOS;

    }
}
