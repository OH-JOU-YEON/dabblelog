package com.dabblelog.side.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {

    //설정 호출 메서드
    @GetMapping("/setting")
    public String mappingSetting(Model model) {
        return "/basic/Setting";
    }

    //좋아요 호출 메서드
    @GetMapping("/liked")
    public String mappingLiked(Model model) {

        return "/basic/Liked";
    }

    //임시 저장 글 호출 메서드
    @GetMapping("/saved")
    public String mappingSaved(Model model) {

        return "/basic/Saves";
    }


    //최근에 읽은 포스트 호출 메서드
    @GetMapping("/recentRead")
    public String mappingRecentRead(Model model) {

        return "/basic/RecentRead";
    }



}
