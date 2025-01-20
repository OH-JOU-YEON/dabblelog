package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SearchController {

    @GetMapping("/search")
    public String locationToSearch(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null ) {
            SessionUser sessionuser = (SessionUser) session.getAttribute("user");
            model.addAttribute("loginOrNot","새 글 작성하기");
            model.addAttribute("path","/write");
            model.addAttribute("email",sessionuser.getEmail());

        } else {
            model.addAttribute("loginOrNot","구글 로그인");
            model.addAttribute("path","/oauth2/authorization/google");
        }


        return "/basic/Search";
    }

    @GetMapping("/search/{}")
    public String getSearchResult(Model model, HttpServletRequest request) {

        return "/basic/Home";
    }
}
