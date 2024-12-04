package com.dabblelog.side.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    static String getEmail(String toString) {
        String[] toStrings = toString.split(",");
        String[] emails = toStrings[7].split("=");
        return emails[1];
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @GetMapping("/blog")
    public String mappingBlog(Model model) {

        //유저의 아이디로 검색한 블로그가 없으면 새로 만들어





        return "basic/Posts";
    }

    @GetMapping("/about")
    public String mappingAbout(Model model) {
        return "basic/About";
    }

    @GetMapping("/series")
    public String mappingSeries(Model model) {
        return "basic/Series";
    }
}
