package com.dabblelog.side.controller;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    BlogService blogService;

    @GetMapping("/") String mappingHome(Model model, @PageableDefault(page=0, size=9, sort="likeCount") Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("email", "dabblelog.com");
        model.addAttribute("myBlogURL", "/oauth2/authorization/google");

        Page<PostHomeDTO> postList = postService.getPostPageable(pageable);

        //페이지블럭 처리
        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
        int nowPage = postList.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage =  Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage+9, postList.getTotalPages());


        model.addAttribute("list", postList);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
            Blog blog = blogService.ifBlogIsNotExistCreateBlog(email);

            model.addAttribute("myBlogName",blog.getBlogName());
            model.addAttribute("myBlogURL", "/dabblelog/" + blog.getBlogName());
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
        model.addAttribute("myBlogURL", "/oauth2/authorization/google");
        Page<PostHomeDTO> postList = postService.getPostPageable(pageable);
        model.addAttribute("email", "dabblelog.com");
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

        if( !auth.getPrincipal().toString().equals("anonymousUser") ) {

            //로그인 됐을 때

            String principal = auth.getPrincipal().toString();


            //파싱

            String email = getEmail(principal);
            Blog blog = blogService.ifBlogIsNotExistCreateBlog(email);

            model.addAttribute("path", "/write");
            model.addAttribute("loginOrNot", "새 글 작성하기");
            model.addAttribute("email",email);
            model.addAttribute("myBlogURL", "/dabblelog/" + blog.getBlogName());

        }else {

            //로그인 아닐 때
            model.addAttribute("path", "/oauth2/authorization/google");
            model.addAttribute("loginOrNot", "구글 로그인");

        }
        return "basic/Home";
    }

    //최신 글 처리
    @GetMapping("/new")
    String mappingNew(Model model, @PageableDefault(page=0, size=9, sort="createdDay", direction= Sort.Direction.DESC) Pageable pageable) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("myBlogURL", "/oauth2/authorization/google");
        model.addAttribute("email", "dabblelog.com");
        Page<PostHomeDTO> postList = postService.getPostPageable(pageable);
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

        if( !auth.getPrincipal().toString().equals("anonymousUser") ) {

            //로그인 됐을 때

            String principal = auth.getPrincipal().toString();

            //파싱
            String[] principals = principal.split(",");
            String[] emails = principals[7].split("=");
            String email = emails[1];
            Blog blog = blogService.ifBlogIsNotExistCreateBlog(email);
            model.addAttribute("path", "/write");
            model.addAttribute("loginOrNot", "새 글 작성하기");
            model.addAttribute("email",email);
            model.addAttribute("myBlogURL", "/dabblelog/" + blog.getBlogName());

        }else {

            //로그인 아닐 때
            model.addAttribute("path", "/oauth2/authorization/google");
            model.addAttribute("loginOrNot", "구글 로그인");


        }
        return "basic/Home";
    }

    //피드 처리
    @GetMapping("/feed")
    String mappingFeed(Model model, @PageableDefault(page=0, size=9, sort="createdDay" , direction= Sort.Direction.DESC) Pageable pageable) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("myBlogURL", "/oauth2/authorization/google");
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
            Page<PostHomeDTO> postList = postService.getFeed(pageable, email);
            model.addAttribute("pageList",postList);
            //페이지블럭 처리
            //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
            int nowPage = postList.getPageable().getPageNumber() + 1;
            //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
            int startPage =  Math.max(nowPage - 4, 1);
            int endPage = Math.min(nowPage+9, postList.getTotalPages());
            model.addAttribute("nowPage",nowPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            Blog blog = blogService.ifBlogIsNotExistCreateBlog(email);
            model.addAttribute("myBlogURL", "/dabblelog/" + blog.getBlogName());



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
