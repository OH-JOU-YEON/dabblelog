package com.dabblelog.side.controller;

import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.FollowDTO;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.FollowerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FollowController {


   private final FollowerService followerService;



   private final BlogService blogService;

    @PostMapping("/follow")
    public void followingUpdate(HttpServletRequest request, @RequestBody FollowDTO followDTO){

        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        String blogName = getBlogNameToUrl(followDTO.getUrl());

        log.info("blogName : " + blogName);




        if(followDTO.getFollowOrNot().equals("팔로잉")) {

            followerService.createFollower(sessionUser,blogName);
        }else {

            followerService.deleteFollower(sessionUser,blogName);
        }



    }

    private String getBlogNameToUrl(String url) {

        String[] parseURL = url.split("/");

        return parseURL[4];
    }


}
