package com.dabblelog.side.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {

    @GetMapping("/setting")
    public String mappingSetting(Model model) {
        return "/basic/Setting";
    }

    //프로필 이미지 수정하는 서비스 호출 메서드

    //블로그 제목 수정 메서드

    //이메일 주소 보여주는 메서드


}
