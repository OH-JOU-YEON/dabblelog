package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Dabble;
import com.dabblelog.side.domain.dto.DabbleDTO;
import com.dabblelog.side.domain.dto.DabblePostDTO;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.DabbleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DabbleController {

    @Autowired
    DabbleService dabbleService;

    @Autowired
    BlogService blogService;

    @GetMapping("/dabble")
    public String getDabbles(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null ) {
            SessionUser sessionuser = (SessionUser) session.getAttribute("user");
            model.addAttribute("loginOrNot","새 글 작성하기");
            model.addAttribute("path","/write");
            model.addAttribute("email",sessionuser.getEmail());

            Blog blog = blogService.ifBlogIsNotExistCreateBlog(sessionuser.getEmail());

            LocalDateTime localDateTime = LocalDateTime.now();

            Dabble dabble = dabbleService.getDabbleByDate(localDateTime);




        } else {
            return "redirect:/";
        }

        return "/basic/Dabble";
    }
}
