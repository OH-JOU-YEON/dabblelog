package com.dabblelog.side.controller;


import com.dabblelog.side.service.impl.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    PostService postService;

    @GetMapping("/") String mappingHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("email", "dabblelog.com");

        if( !auth.getPrincipal().toString().equals("anonymousUser") ) {

            //로그인 됐을 때

            String principal = auth.getPrincipal().toString();

            //파싱
            String[] principals = principal.split(",");
            String[] emails = principals[7].split("=");
            String email = emails[1];
            model.addAttribute("path", "/write");
            model.addAttribute("loginOrNot", "새 글 작성하기");
            model.addAttribute("email",email);
            model.addAttribute("user_name",getUserName(email));
        }else {

            //로그인 아닐 때
            model.addAttribute("path", "/oauth2/authorization/google");
            model.addAttribute("loginOrNot", "구글 로그인");

        }


        return "basic/Home";
    }


    //홈 화면 관련 로직


    //트렌딩 처리
    @GetMapping("/trend")
    String mappingTrend(Model model, @PageableDefault(page=0, size=9, sort="likeCount") Pageable pageable) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("email", "dabblelog.com");

        if( !auth.getPrincipal().toString().equals("anonymousUser") ) {

            //로그인 됐을 때

            String principal = auth.getPrincipal().toString();

            //파싱

            String email = getEmail(principal);

            model.addAttribute("path", "/write");
            model.addAttribute("loginOrNot", "새 글 작성하기");
            model.addAttribute("email",email);
            model.addAttribute("pageList",postService.getPostPageable(pageable));
        }else {

            //로그인 아닐 때
            model.addAttribute("path", "/oauth2/authorization/google");
            model.addAttribute("loginOrNot", "구글 로그인");
            model.addAttribute("pageList",postService.getPostPageable(pageable));
        }
        return "basic/Home";
    }

    //최신 글 처리
    @GetMapping("/new")
    String mappingNew(Model model, @PageableDefault(page=0, size=9, sort="createdDay", direction= Sort.Direction.DESC) Pageable pageable) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("email", "dabblelog.com");

        if( !auth.getPrincipal().toString().equals("anonymousUser") ) {

            //로그인 됐을 때

            String principal = auth.getPrincipal().toString();

            //파싱
            String[] principals = principal.split(",");
            String[] emails = principals[7].split("=");
            String email = emails[1];
            model.addAttribute("path", "/write");
            model.addAttribute("loginOrNot", "새 글 작성하기");
            model.addAttribute("email",email);
            model.addAttribute("pageList",postService.getPostPageable(pageable));
        }else {

            //로그인 아닐 때
            model.addAttribute("path", "/oauth2/authorization/google");
            model.addAttribute("loginOrNot", "구글 로그인");
            model.addAttribute("pageList",postService.getPostPageable(pageable));

        }
        return "basic/Home";
    }

    //피드 처리
    @GetMapping("/feed")
    String mappingFeed(Model model, @PageableDefault(page=0, size=9, sort="createdDay" , direction= Sort.Direction.DESC) Pageable pageable) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("email", "dabblelog.com");

        if( !auth.getPrincipal().toString().equals("anonymousUser") ) {

            //로그인 됐을 때

            String principal = auth.getPrincipal().toString();

            //파싱
            String[] principals = principal.split(",");
            String[] emails = principals[7].split("=");
            String email = emails[1];
            model.addAttribute("path", "/write");
            model.addAttribute("loginOrNot", "새 글 작성하기");
            model.addAttribute("email",email);
            model.addAttribute("feed",postService.getFeed(pageable,email));

        }else {

            //로그인 아닐 때
            model.addAttribute("path", "/oauth2/authorization/google");
            model.addAttribute("loginOrNot", "구글 로그인");

        }
        return "basic/Home";
    }

    static String getEmail(String principal) {

        String[] principals = principal.split(",");
        String[] emails = principals[7].split("=");

        return emails[1];

    }



    static String getUserName(String email) {
        String[] emails = email.split("@");
        return emails[0];
    }

}
