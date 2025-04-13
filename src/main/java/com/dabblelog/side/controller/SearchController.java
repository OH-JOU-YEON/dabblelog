package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.service.impl.SearchService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {


   private final SearchService searchService;

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

    @GetMapping("/search-result")
    public String getSearchResult(Model model, HttpServletRequest request, @PageableDefault(page=0, size=9) Pageable pageable) {

        HttpSession session = request.getSession(false);

        if(session != null ) {
            SessionUser sessionuser = (SessionUser) session.getAttribute("user");
            model.addAttribute("loginOrNot","새 글 작성하기");
            model.addAttribute("path","/write");
            model.addAttribute("email",sessionuser.getEmail());
            model.addAttribute("myBlogURL", "/oauth2/authorization/google");

        } else {
            model.addAttribute("loginOrNot","구글 로그인");
            model.addAttribute("path","/oauth2/authorization/google");
            model.addAttribute("email", "dabblelog.com");
            model.addAttribute("myBlogURL", "/oauth2/authorization/google");
        }



        Page<PostHomeDTO> postList = searchService.getPostHomeDTOS(request.getParameter("search"),pageable);
        model.addAttribute("list",postList);
        //페이지블럭 처리
        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
        int nowPage = postList.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, postList.getTotalPages());
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);



        return "/basic/SearchResult";
    }
}
