package com.dabblelog.side.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping("/blog")
    public String mappingBlog(Model model) {

        //로그인 되어 있는지 검사 후에 로그인 돼 있으면 프로필 창 가져오는 메서드

        //일단 트렌딩에 맞춰서 좋아요 개수가 많은 이번 주 게시물들을 불러오는 메서드


        return "basic/Posts";
    }

    @GetMapping("/about")
    public String mappingAbout(Model model) {
        return "basic/About";
    }

    @GetMapping("/series")
    public String mappingSeries(Model model) {
        return "basic/Series";
    }
}
