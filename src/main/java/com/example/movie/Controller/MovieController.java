package com.example.movie.Controller;


import com.example.movie.DTO.MovieDTO;
import com.example.movie.Service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log
@Tag(name = "영화정보 클래스",description = "영화정보와 이미지파일을 관리")
public class MovieController {
    private final MovieService movieService;

    //7개(삽입(2)), 수정(2) 상세보기(1) 목록(1) 삭제(1)
    //삽입
    @Operation(summary = "영화등록",description = "영화정보르를 등록하는 폼으로 이동")
    @GetMapping("/insert")
    public String insertView(){
        log.info("영화정보 등록폼으로 이동");
        return "write";

    }
    @Operation(summary = "영화등록",description = "영화정보르를 데이터베이스에 저장")
    @PostMapping("/insert")
    public String insert(MovieDTO movieDTO, MultipartFile file){
        log.info("영화정보와 파일을 받아서 서비스에 전달해서 저장");
        movieService.write(movieDTO,file);
        return "redirect:/list";


    }
    //수정
    @Operation(summary = "영화수정", description = "영화정보를 수정하는 폼으로 이동")
    @GetMapping("/update")
    public String updateView(Integer code, Model model) {
        log.info("수정할 번호를 받아서 해당 DTO를 폼에 전달");
        MovieDTO movieDTO = movieService.detail(code);
        model.addAttribute("data", movieDTO);

        return "update";
    }
    @Operation(summary = "영화수정",description = "영화정보르를 데이터베이스에 저장")
    @PostMapping("/update")
    public String update(MovieDTO movieDTO,MultipartFile file){
        log.info("수정할 번호를 받아서 해다 DTO를 폼에 전달");
        movieService.update(movieDTO,file);
        return "redirect:/list";

    }


    //상세보기
    @Operation(summary = "영화상세보기", description = "지정된 영화정보를 읽어와 폼에 출력")
    @GetMapping("/detail")
    public String detail(Integer code, Model model) {
        log.info("번호를 받아서 서비스에 해당자료를 받아서 폼에 전달");
        MovieDTO movieDTO = movieService.detail(code);
        model.addAttribute("data", movieDTO);

        return "detail";
    }
    //목록
    @Operation(summary = "영화목록보기", description = "모든 영화정보를 읽어와 폼에 출력")
    @GetMapping({"/","/list"})
    public String list(Model model) {
        log.info("서비스에 전체자료를 받아서 폼에 전달");
        List<MovieDTO> movieDTOList = movieService.list();
        model.addAttribute("list", movieDTOList);

        return "list";
    }
    //삭제
    @Operation(summary = "영화삭제", description = "지정된 영화정보를 데이터베이스에서 삭제")
    @GetMapping("/delete")
    public String delete(Integer code) {
        log.info("번호를 받아서 서비스에서 데이터를 삭제");
        movieService.delete(code);

        return "redirect:/list";
    }
}
