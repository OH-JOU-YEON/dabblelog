package com.dabblelog.side.controller;


import com.dabblelog.side.domain.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WriteController {

    @GetMapping("/write")
    public String writeMapping(Model model) {



        return "basic/write";
    }

    @PostMapping("/write")
    public Post createPost(Model model) {

    }
}
