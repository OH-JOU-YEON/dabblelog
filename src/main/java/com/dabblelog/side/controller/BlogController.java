package com.dabblelog.side.controller;


import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BlogController {
    @Autowired
   final BlogService blogService;
    @Autowired
   final UserRepository userRepository;




    static String getEmail(String toString) {
        String[] toStrings = toString.split(",");
        String[] emails = toStrings[7].split("=");
        return emails[1];
    }



    @GetMapping("/blog")
    public String mappingBlog(Model model) {





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
