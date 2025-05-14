package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Series;
import com.dabblelog.side.domain.dto.SavesDetailsDTO;
import com.dabblelog.side.repository.PostTagRepository;
import com.dabblelog.side.repository.SeriesRepository;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.PostTagService;
import com.dabblelog.side.service.impl.SaveService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class WriteController {


    private final SeriesRepository seriesRepository;


   private final SaveService saveService;


   private final BlogService blogService;

   private final PostTagService postTagService;



    @GetMapping("/write")
    public String writeMapping(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        //세션이 만약 없으면 홈으로 돌려보냄

        if(session == null ) {
            return "basic/home";
        }

        SessionUser sessionuser = (SessionUser) session.getAttribute("user");





        Blog blog = blogService.ifBlogIsNotExistCreateBlog(sessionuser.getEmail());

        //레포지토리에서 블로그 시리즈 전부 찾아다 모델에 집어넣는 메서드

        List<Series> seriesList = seriesRepository.findAllByBlogId(blog);

        //시리즈 검사하는 로직

        seriesListCheck(seriesList,model);

        model.addAttribute("myblogURL","/dabblelog/" + blog.getBlogName());

        model.addAttribute("seriesList", seriesList);


        return "basic/write";
    }

    @GetMapping("/write/{blogName}/{uuid}")
    public String writeTempMapping(Model model, HttpServletRequest request, @PathVariable String blogName,
                                   @PathVariable String uuid) {

        HttpSession httpSession = request.getSession(false);

        if(httpSession == null) {

            return "redirect:/";
        }

        SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");

        Blog blog = blogService.getBlogByName(blogName);

        List<Series> seriesList = seriesRepository.findAllByBlogId(blog);

        //시리즈 검사하는 로직

        seriesListCheck(seriesList,model);

        SavesDetailsDTO savesDetailsDTO = saveService.getSavesDetails(uuid,blog);

        model.addAttribute("save",savesDetailsDTO);

        model.addAttribute("myblogURL","/dabblelog/" + blog.getBlogName());

        model.addAttribute("seriesList", seriesList);






        return "basic/Modify";
    }


    public void seriesListCheck(List<Series> seriesList,Model model) {

        if(seriesList.isEmpty()) {
            model.addAttribute("seriesList","만든 시리즈가 없습니다");
        }
    }


}
