package com.dabblelog.side.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeriesController {


    @GetMapping("/series")
    public String mappingHome(Model model) {


        return "/basic/Series";
    }
}
