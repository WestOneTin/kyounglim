package com.kyounglim.www.util;

import com.kyounglim.www.domain.photo.Photo;
import com.kyounglim.www.dto.photo.PhotoSaveRequestDto;
import com.kyounglim.www.service.PhotoService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandler {

    private final PhotoService photoService;

    public FileHandler(PhotoService photoService) {
        this.photoService = photoService;
    }

    public Photo parseFileInfo(MultipartFile multipartFile) throws Exception {
        Photo photo = null;
        if (!multipartFile.isEmpty()) { // 파일이 존재할경우
            // 파일명을 업로드 한 날짜로 변환하여 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            /* // 프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
            // 경로 구분자 File.separator 사용
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            // 파일을 저장할 세부 경로 지정
            String path = "images" + File.separator + current_date;
            File file = new File(path);

            // 디렉터리가 존재하지 않을 경우
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();

                // 디렉터리 생성에 실패했을 경우
                if(!wasSuccessful)
                    System.out.println("file: was not successful");
            }
            // 파일의 확장자 추출
            String originalFileExtension;
            String contentType = multipartFile.getContentType();
            String fileName = new MD5Generator(multipartFile.getOriginalFilename()).toString() + ".jpg";

            // 확장자명이 존재하지 않을 경우 처리 x
            if(ObjectUtils.isEmpty(contentType)) {
                return null;
            }
            else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                if(contentType.contains("image/jpeg"))
                    originalFileExtension = ".jpg";
                else if(contentType.contains("image/png"))
                    originalFileExtension = ".png";
                else  // 다른 확장자일 경우 처리 x
                    return null;
            }

            // 파일명 중복 피하고자 나노초까지 얻어와 지정
            String new_file_name = System.nanoTime() + originalFileExtension;

            photo = Photo.builder().filename(fileName).origFilename(multipartFile.getOriginalFilename()).filePath(path + File.separator + new_file_name).build();

            // 업로드 한 파일 데이터를 지정한 파일에 저장
            file = new File(absolutePath + path + File.separator + new_file_name);*/

            // -----------------------------------------------------------------------------------------------
            // 프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
            // 경로 구분자 File.separator 사용
            //String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            // 파일을 저장할 세부 경로 지정
            //String path = "images" + File.separator + current_date;
            // windows
            //String path = System.getProperty("user.dir") + "\\img";
            // MAC
            String path = System.getProperty("user.dir") + "/src/main/resources/static/img";
            File file = new File(path);

            // 디렉터리가 존재하지 않을 경우
            if (!file.exists()) {
                boolean wasSuccessful = file.mkdirs();
                // 디렉터리 생성에 실패했을 경우
                if (!wasSuccessful) {
                    System.out.println("file: was not successful");
                }
            }
            // 파일의 확장자 추출
            String originalFileExtension;
            String contentType = multipartFile.getContentType();

            // 확장자명이 존재하지 않을 경우 처리 x
            if (ObjectUtils.isEmpty(contentType)) {
                return null;
            } else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                if (contentType.contains("image/jpeg"))
                    originalFileExtension = ".jpg";
                else if (contentType.contains("image/png"))
                    originalFileExtension = ".png";
                else  // 다른 확장자일 경우 처리 x
                    return null;
            }
            String file_name = new MD5Generator(multipartFile.getOriginalFilename()).toString() + originalFileExtension;

            photo = Photo.builder().origFilename(multipartFile.getOriginalFilename()).filePath(path + File.separator + file_name).build();

            // 업로드 한 파일 데이터를 지정한 파일에 저장
            //file = new File(absolutePath + path + File.separator + new_file_name);
            // windows
            //file = new File(path + "\\" + new_file_name);
            // MAC
            file = new File(path + "/" + file_name);
            multipartFile.transferTo(file);

            // 파일 권한 설정(쓰기, 읽기)
            file.setWritable(true);
            file.setReadable(true);
        }
        return photo;
    }


}
