package com.dabblelog.side.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    //개별 페이지 관련 로직들
    @GetMapping("/post")
    public String mappingHome(Model model, HttpServletRequest request) {


        return "/basic/Post";
    }



    //댓글처리
}
