package com.example.movie.Util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;


//개인이 개별적으로 만든 메소드를 등록(Controller,Service에 주로 사용)
@Component
public class FileUpload {
    @Value("${imgUpload}")//이미지가 저장될 실질적인 위치

    private String imgLocation;
    //2개 파일을 저장, 파일을 삭제
    public String FileUpload(MultipartFile imageFile){  //저장할 파일을 받아서 지정한 위치에 저장
        //파일에 이름만 불리->새이름과 확장자를 결합해서 저장
        String oriFileName = imageFile.getOriginalFilename(); //파일명읽기 poster.jpg
        String extension = oriFileName.substring(oriFileName.lastIndexOf(".")); //확장자 불리 .jpg
        UUID uuid = UUID.randomUUID(); // 난수로 새이름생성 12345-64551456
        String saveFileName = uuid.toString()+extension; //새파일명 12345-64551456.jpg
        String uploadUrl = imgLocation+saveFileName;//c:movie/poster/12345-64551456.jpg

        //저장작업
        try

        {
            File folder = new File(imgLocation);
            if (!folder.exists()){
                boolean result =folder.mkdirs();
            }
            byte[] filedata = imageFile.getBytes(); //파일을 읽어서
            FileOutputStream fos = new FileOutputStream(uploadUrl);
            fos.write(filedata);
            fos.close();

        } catch(Exception e){
            return null;
        }


        return saveFileName; //새로운 파일명을 전달
    }
    public void  FileDelete(String imgeFileName){  // 지정한 파일을 찾아서 삭제
        String deleteFileName = imgLocation+imgeFileName;

        try {
            File deleteFile = new File(deleteFileName);
            if (deleteFile.exists()){
                deleteFile.delete();
            }

        }catch (Exception e){

        }

    }
}
