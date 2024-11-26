package com.dabblelog.side.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    String mappingHome(Model model) {

        return "basic/Home";
    }

    @GetMapping("/login")
    String mappingLogin(Model model) {
        return "basic/Login";
    }



}
