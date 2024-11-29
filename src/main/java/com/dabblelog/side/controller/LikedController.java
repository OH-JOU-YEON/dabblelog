package com.dabblelog.side.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LikedController {

    //내가 좋아요 누른 게시물들을 볼 수 있는 페이지

    @GetMapping("/liked_list")
    public String mappingHome(Model model) {



        return "basic/Liked";
    }
}
