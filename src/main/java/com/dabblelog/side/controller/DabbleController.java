package com.dabblelog.side.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DabbleController {

    @GetMapping("/dabble")
    public String getDabbles() {

        return "/basic/Dabble";
    }
}
