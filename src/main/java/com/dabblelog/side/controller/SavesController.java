package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.dto.SavesDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.SaveService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SavesController {

    @Autowired
    SaveService saveService;
    //임시 저장 페이지 관련 로직들

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/saves")
    public String mappingHome(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        //세션이 만약 없으면 홈으로 돌려보냄

        if(session == null ) {
            return "redirect:/";
        }

        SessionUser sessionuser = (SessionUser) session.getAttribute("user");




        String email = sessionuser.getEmail();

        Blog blog = blogRepository.findById(userRepository.findByEmail(email).get().getId()).get();

        List<SavesDTO> savesDTOList = saveService.getSaves(email);

        model.addAttribute("email",email);

        model.addAttribute("savesList",savesDTOList);

        model.addAttribute("myBlogURL","/dabblelog/" + blog.getBlogName());





        return "/basic/Saves";
    }
}
