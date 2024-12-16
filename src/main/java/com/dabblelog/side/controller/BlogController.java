package com.dabblelog.side.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BlogController {

    @GetMapping("/{user_name}/posts")
    public String blogMapping(Model model, @PathVariable("user_name") String user_name) {

        return "basic/Posts";
    }

}
