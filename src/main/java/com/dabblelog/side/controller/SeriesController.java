package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.SeriesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class SeriesController {

    @Autowired
    SeriesService seriesService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogRepository blogRepository;

@PostMapping("/series")
    public String seriesCreate(Model model, HttpServletRequest request) {

    //세션 유저에서 유저 가져와서 유저 아이디 알아내기

    //서비스에 폼이랑 블로그 아이디 넣어주기

    HttpSession session = request.getSession(false);

    if(session == null ) {
        return "basic/home";
    }

    SessionUser sessionuser = (SessionUser) session.getAttribute("user");

    User user = userRepository.findByEmail(sessionuser.getEmail()).get();

    Blog blog = blogRepository.findById(user.getId()).get();

    String color = request.getParameter("color");

    String title = request.getParameter("title");

    seriesService.createSeries(blog, color, title);



    return "basic/Series";
}


    //시리즈 생성 데이터 받아서 파싱해다가 시리즈 생성하는 로직


    //시리즈 수정 데이터 받아서 파싱해다가 시리즈 수정하는 로직


    //시리즈 삭제하는 로직




}
