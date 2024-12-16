package com.dabblelog.side.controller;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String blogMapping(Model model, @PathVariable("user_name") String user_name) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String principal = auth.getPrincipal().toString();

        String email = HomeController.getEmail(principal);

        Blog blog = updateBlog(email);

        model.addAttribute("blog",blog);


        return "basic/Posts";
    }

    //레포지토리에 유저를 가진 블로그가 있는지 검색 없으면 생성

    Blog updateBlog(String email) {
        User user = userRepository.findByEmail(email).get();

        Optional<Blog> blogWrapper = blogRepository.findByUser(user);

        if(blogWrapper.isPresent()) {
            return new Blog(user);
        } else {
            return blogWrapper.get();
        }
    }

}
