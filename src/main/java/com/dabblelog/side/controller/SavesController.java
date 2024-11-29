package com.dabblelog.side.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SavesController {
    //임시 저장 페이지 관련 로직들

    @GetMapping("/saves")
    public String mappingHome(Model model) {


        return "/basic/Saves";
    }
}
