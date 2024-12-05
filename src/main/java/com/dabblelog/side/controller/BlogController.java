package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.UserLoginStayDto;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.BlogService;
import jakarta.servlet.http.HttpSession;
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



    @Autowired
    HttpSession httpSession;

    static String getEmail(String toString) {
        String[] toStrings = toString.split(",");
        String[] emails = toStrings[7].split("=");
        return emails[1];
    }



    @GetMapping("/blog")
    public String mappingBlog(Model model) {


        if(httpSession.getAttribute("user")!= null) {
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            String email = sessionUser.getEmail();
            User user = userRepository.findByEmail(email).get();
            UserLoginStayDto userDto = new UserLoginStayDto(user);

            model.addAttribute("userDto", userDto);
        }
        else {
            return "oauth2/authorization/google";
        }



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
