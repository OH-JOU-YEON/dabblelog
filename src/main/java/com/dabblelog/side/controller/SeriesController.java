package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.getSeriesDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.SeriesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String seriesCreate(Model model, HttpServletRequest request, @PathVariable String blogName) {

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

    @GetMapping("/series")
    public String mappingSeries(Model model, HttpServletRequest request, @PageableDefault(page=0, size=6, sort="seriesLastUpdateDay"
    , direction= Sort.Direction.DESC) Pageable pageable) {

        HttpSession session = request.getSession(false);

        //세션이 만약 없으면 홈으로 돌려보냄

        if(session == null ) {
            return "basic/home";
        }

        SessionUser sessionuser = (SessionUser) session.getAttribute("user");


        String email = sessionuser.getEmail();

        Page<getSeriesDTO> getSeriesDTOS = seriesService.getSeries(email,pageable);

        //페이지블럭 처리
        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
        int nowPage = getSeriesDTOS.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, getSeriesDTOS.getTotalPages());
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        model.addAttribute("email", email);

        model.addAttribute("seriesList",getSeriesDTOS);




        return "basic/Series";
    }


    //시리즈 생성 데이터 받아서 파싱해다가 시리즈 생성하는 로직


    //시리즈 수정 데이터 받아서 파싱해다가 시리즈 수정하는 로직


    //시리즈 삭제하는 로직




}
