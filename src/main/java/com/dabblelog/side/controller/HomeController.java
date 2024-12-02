package com.dabblelog.side.controller;



import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {


    @GetMapping String mappingHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if( !auth.getPrincipal().toString().equals("anonymousUser") ) {



            String principal = auth.getPrincipal().toString();

            //파싱
            String[] principals = principal.split(",");
            String[] emails = principals[7].split("=");
            String email = emails[1];
            model.addAttribute("path", "/write");
            model.addAttribute("loginOrNot", "새 글 작성하기");
            model.addAttribute("email",email);
        }else {

            model.addAttribute("path", "/oauth2/authorization/google");
            model.addAttribute("loginOrNot", "구글 로그인");
            model.addAttribute("email", "hyperclutter@gmail.com");
        }


        return "basic/Home";
    }


    //홈 화면 관련 로직


    //트렌딩 처리
    @GetMapping("/trend")
    String mappingTrend(Model model) {

        return "basic/Home";
    }

    //최신 글 처리
    @GetMapping("/new")
    String mappingNew(Model model) {
        return "basic/Home";
    }

    //피드 처리
    @GetMapping("/feed")
    String mappingFeed(Model model) {
        return "basic/Home";
    }




}
