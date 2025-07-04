package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.*;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.RepleRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.PostService;
import com.dabblelog.side.service.impl.RepleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class RepleController {


    private final RepleService repleService;

    private final BlogService blogService;


   private final UserRepository userRepository;

   private final PostRepository postRepository;



   private final PostService postService;


   private final RepleRepository repleRepository;

    @ResponseBody
    @PostMapping("/reple/create")
    public RepleProfileDTO createReple(HttpServletRequest request, @RequestBody RepleCreateDTO repleCreateDTO) {
        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        String email = sessionUser.getEmail();

        User user = userRepository.findByEmail(email).get();

        Post post = postService.getPostIdByURL(repleCreateDTO.getUrl(),repleCreateDTO.getUuid());

        Reple reple = repleRepository.save(new Reple(post,user, LocalDateTime.now(), repleCreateDTO.getContent()));


        return repleService.getRepleAuthor(email,reple);



    }

    @ResponseBody
    @PostMapping("/reple/delete")
    public void deleteReple(HttpServletRequest request, @RequestBody RepleDeleteDTO repleDeleteDTO){

        Blog blog = blogService.getBlogByName(repleDeleteDTO.getBlogName());

        Post post = postRepository.findByUuidAndBlogId(repleDeleteDTO.getPostUuid(),blog);

        Optional<Reple> reple = repleRepository.findByPostIdAndUuid(post, repleDeleteDTO.getUuid());

        reple.ifPresent(repleRepository::delete);

    }

    @ResponseBody
    @PostMapping("/reple/modify")
    public void modifyReple(HttpServletRequest request, @RequestBody RepleModifyDTO repleModifyDTO){

        Blog blog = blogService.getBlogByName(repleModifyDTO.getBlogName());

        Post post = postRepository.findByUuidAndBlogId(repleModifyDTO.getPostUuid(), blog);

        Optional<Reple> reple = repleRepository.findByPostIdAndUuid(post, repleModifyDTO.getUuid());

        reple.ifPresent(value -> repleService.modifyReple(value, repleModifyDTO.getContent()));

    }


}
