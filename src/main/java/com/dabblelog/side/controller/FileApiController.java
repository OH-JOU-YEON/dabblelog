package com.dabblelog.side.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@Slf4j
public class FileApiController {
    String path = System.getProperty("user.dir");

    // 파일을 업로드할 디렉터리 경로
    private final String uploadDir = Paths.get(path , "/side/src/main/resources/static","tui-editor", "upload").toString();

    /**
     * 에디터 이미지 업로드
     * @param image 파일 객체
     * @return 업로드된 파일명
     *
     */
    @ResponseBody
    @PostMapping("tui-editor/image-upload")
    public String uploadEditorImage(@RequestParam final MultipartFile image) {
        if (image.isEmpty()) {
            return "";
        }

        String orgFilename = image.getOriginalFilename();
        log.info("orgFilename : " + orgFilename);
        // 원본 파일명
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        log.info("uuid : " + uuid);// 32자리 랜덤 문자열
        String extension = orgFilename.substring(orgFilename.lastIndexOf(".") + 1);// 확장자
        log.info("extension : " + extension);
        String saveFilename = uuid + "." + extension;                                             // 디스크에 저장할 파일명
        String fileFullPath = Paths.get(uploadDir, saveFilename).toString();
        log.info("fileFullPath : " + fileFullPath);// 디스크에 저장할 파일의 전체 경로

        // uploadDir에 해당되는 디렉터리가 없으면, uploadDir에 포함되는 전체 디렉터리 생성
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            // 파일 저장 (write to disk)
            File uploadFile = new File(fileFullPath);
            image.transferTo(uploadFile);
            log.info("저장된 파일 경로 "+ saveFilename );
            return saveFilename;

        } catch (IOException e) {
            // 예외 처리는 따로 해주는 게 좋습니다.
            throw new RuntimeException(e);
        }
    }

    /**
     * 디스크에 업로드된 파일을 byte[]로 반환
     * @param filename 디스크에 업로드된 파일명
     * @return image byte array
     */
    @GetMapping(value = "image-print", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public byte[] printEditorImage(@RequestParam final String filename) {
        // 업로드된 파일의 전체 경로
        String fileFullPath = Paths.get(uploadDir, filename).toString();
        log.info(fileFullPath);

        // 파일이 없는 경우 예외 throw
        File uploadedFile = new File(fileFullPath);
        if (!uploadedFile.exists()) {
            throw new RuntimeException();
        }

        try {
            // 이미지 파일을 byte[]로 변환 후 반환
            return Files.readAllBytes(uploadedFile.toPath());

        } catch (IOException e) {
            // 예외 처리는 따로 해주는 게 좋습니다.
            throw new RuntimeException(e);
        }
    }

}

