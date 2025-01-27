package com.dabblelog.side.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SettingController {

    //설정 호출 메서드
    @GetMapping("/setting")
    public String mappingSetting(Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession(false);

        if(httpSession == null) {

            return "redirect:/";
        }


        return "/basic/Setting";
    }

    @PostMapping("/setting/profileText")
    public void modifyProfileText() {


    }





}
