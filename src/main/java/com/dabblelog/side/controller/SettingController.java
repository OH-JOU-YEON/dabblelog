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
}
