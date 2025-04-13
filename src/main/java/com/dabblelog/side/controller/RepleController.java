package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.ReRepleDTO;
import com.dabblelog.side.domain.dto.RepleCreateDTO;
import com.dabblelog.side.domain.dto.RepleSendDTO;
import com.dabblelog.side.domain.dto.ReplyDTO;
import com.dabblelog.side.repository.RepleRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.PostService;
import com.dabblelog.side.service.impl.RepleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class RepleController {


    RepleService repleService;


    BlogService blogService;


    UserRepository userRepository;


    PostService postService;


    RepleRepository repleRepository;

    @ResponseBody
    @PostMapping("/reple/create")
    public ReRepleDTO createReple(HttpServletRequest request, @RequestBody RepleCreateDTO repleCreateDTO) {
        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        User user = userRepository.findByEmail(sessionUser.getEmail()).get();

        Post post = postService.getPostIdByURL(repleCreateDTO.getUrl());

        Reple reple = repleRepository.save(new Reple(post,user, LocalDateTime.now(), repleCreateDTO.getContent()));

        return new ReRepleDTO(reple,"/dabblelog/" + blogService.getBlogName(sessionUser.getEmail()));



        //만들고 답글 dto 던져서
    }

    @ResponseBody
    @PostMapping("/reple/reply")
    public RepleSendDTO createReReple(HttpServletRequest request, @RequestBody ReplyDTO replyDTO) {

        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        Reple reple = repleService.createReple(replyDTO);

        String authorBlog = "/dabblelog/" + blogService.getBlogName(sessionUser.getEmail());


        return new RepleSendDTO(reple,authorBlog);

    }
}
