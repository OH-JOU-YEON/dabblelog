package com.dabblelog.side.controller;


import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.UserDto;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    BlogService blogService;
    @Autowired
    UserRepository userRepository;

    static String getEmail(String toString) {
        String[] toStrings = toString.split(",");
        String[] emails = toStrings[7].split("=");
        return emails[1];
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String userEmail = getEmail(auth.toString());

    @GetMapping("/blog")
    public String mappingBlog(Model model) {

        //유저의 아이디로 검색한 블로그가 없으면 새로 만들기 있으면 그냥 냅둠
        User user = userRepository.findByEmail(userEmail).get();
        //
        blogService.createBlog(user);

        UserDto userDto = new UserDto(user);

        model.addAttribute("userDto", userDto);




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
