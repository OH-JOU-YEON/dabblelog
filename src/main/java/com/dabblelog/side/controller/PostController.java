package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    //개별 페이지 관련 로직들
    @GetMapping("/post")
    public String getPost(Model model, HttpServletRequest request) {


        return "/basic/Post";
    }

    @PostMapping("post/tempCreate")
    public String createTemp(Model model, HttpServletRequest request) {

        //세션 얻어서 검사

        HttpSession session = request.getSession(false);

        if(session == null ) {
            return "basic/home";
        }


        // 블로그 얻어옴
        SessionUser sessionuser = (SessionUser) session.getAttribute("user");

        User user = userRepository.findByEmail(sessionuser.getEmail()).get();

        Blog blog = blogRepository.findById(user.getId()).get();

        String title = request.getParameter("title");

        String content = request.getParameter("content");

        String tags = request.getParameter("tag");

        return "redirect:/post";
    }



    @PostMapping("/post/create")
    public String createPost(Model model, HttpServletRequest request) {


        //세션 얻어서 검사

        HttpSession session = request.getSession(false);

        if(session == null ) {
            return "basic/home";
        }


        // 블로그 얻어옴
        SessionUser sessionuser = (SessionUser) session.getAttribute("user");

        User user = userRepository.findByEmail(sessionuser.getEmail()).get();

        Blog blog = blogRepository.findById(user.getId()).get();



        String title = request.getParameter("title");

        String content = request.getParameter("content");

        String tags = request.getParameter("tag");

        String series = request.getParameter("series");

        return "redirect:/post";
    }


    //태그 처리하고 매핑 시키는 메서드






}
