package com.dabblelog.side.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WriteController {

    @GetMapping("/write")
    public String writeMapping(Model model) {



        return "basic/write";
    }
}
