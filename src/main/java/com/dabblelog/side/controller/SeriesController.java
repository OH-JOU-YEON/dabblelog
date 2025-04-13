package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.BlogProfileDTO;
import com.dabblelog.side.domain.dto.SeriesDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.SeriesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class SeriesController {


    SeriesService seriesService;


    UserRepository userRepository;


    BlogRepository blogRepository;


    BlogService blogService;

@PostMapping("/series/create")
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

    String email = sessionuser.getEmail();






    model.addAttribute("email", email);





    return "redirect:/dabblelog/" + blogService.getBlogName(email) + "/series";
}

    @GetMapping("/dabblelog/{blogName}/series")
    public String mappingSeries(Model model, HttpServletRequest request, @PageableDefault( size=6) Pageable pageable,
                                @PathVariable String blogName) {

        HttpSession session = request.getSession(false);

        Page<SeriesDTO> seriesDTOS = seriesService.getSeries(blogName,pageable);
        model.addAttribute("seriesList",seriesDTOS);
        model.addAttribute("thisBlog","/dabblelog/"+blogName);


        int nowPage = seriesDTOS.getPageable().getPageNumber() + 1;
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, seriesDTOS.getTotalPages());
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        BlogProfileDTO blogProfileDTO = blogService.getBlogProfileDTO(blogName);

        model.addAttribute("profile",blogProfileDTO);



        if(session == null ) {
            model.addAttribute("email","dabblelog.com");
            model.addAttribute("myBlogURL","/oauth2/authorization/google");


        } else {
            SessionUser sessionUser = (SessionUser) session.getAttribute("user");

            model.addAttribute("email",sessionUser.getEmail());
            model.addAttribute("myBlogURL","/dabblelog/" + blogService.getBlogName(sessionUser.getEmail()));

            if(Objects.equals(blogRepository.findByBlogName(blogName).get().getId(), userRepository.findByEmail(sessionUser.getEmail()).get().getId())) {

                return "basic/Series";
            }

        }
        return "basic/SeriesOther";


    }


    @GetMapping("/dabblelog/{blogName}/series/{seriesTitle}")
    public String getSeriesDetails(Model model, HttpServletRequest request, @PageableDefault( size=6) Pageable pageable,
                                   @PathVariable String blogName, @PathVariable String seriesTitle){

        return "basic/SeriesDetails";

    }


    //시리즈 생성 데이터 받아서 파싱해다가 시리즈 생성하는 로직


    //시리즈 수정 데이터 받아서 파싱해다가 시리즈 수정하는 로직


    //시리즈 삭제하는 로직




}
