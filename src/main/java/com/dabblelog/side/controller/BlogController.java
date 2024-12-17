package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BlogController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/{user_name}/posts")
    public String blogMapping(Model model, @PathVariable("user_name") String user_name, HttpServletRequest
            request) {

        HttpSession session = request.getSession(false);

        //세션이 만약 없으면 홈으로 돌려보냄

        if(session == null ) {
            return "basic/home";
        }

        SessionUser sessionuser = (SessionUser) session.getAttribute("user");


        String email = sessionuser.getEmail();

        model.addAttribute("email", email);


        Blog blog = updateBlog(email);

        model.addAttribute("blog",blog);

        User user = userRepository.findByEmail(email).get();

        model.addAttribute("profileImg",user.getPicture());

        model.addAttribute("name", user.getName());

        model.addAttribute("readme", user.getReadme());



        return "basic/Posts";
    }

    //레포지토리에 유저를 가진 블로그가 있는지 검색 없으면 생성

    Blog updateBlog(String email) {
        User user = userRepository.findByEmail(email).get();


        Optional<Blog> blogWrapper = blogRepository.findById(user.getId());

        return blogWrapper.orElseGet(() -> blogRepository.save(new Blog(user)));
    }

}
