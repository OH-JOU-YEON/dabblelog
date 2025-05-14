package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.*;
import com.dabblelog.side.domain.dto.PostDTO;
import com.dabblelog.side.domain.dto.PostDeleteDTO;
import com.dabblelog.side.domain.dto.PostViewDTO;
import com.dabblelog.side.domain.dto.RepleDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.SeriesRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {


   private final BlogRepository blogRepository;


    private final UserRepository userRepository;


   private final PostService postService;


   private final SeriesRepository seriesRepository;


   private final PostTagService postTagService;


   private final TagMappingService tagMappingService;


   private final BlogService blogService;


   private final RepleService repleService;

   private final FollowerService followerService;


    //개별 페이지 관련 로직들
    @GetMapping("/dabblelog/{blogName}/{UUID}")
    public String getPost(Model model, HttpServletRequest request, @PathVariable String blogName, @PathVariable String UUID) {

        HttpSession session = request.getSession(false);
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");

        //세션이 null이 아니고 로그인 돼 있을 때


        String userBlog = blogService.getBlogName(sessionUser.getEmail());
        model.addAttribute("email",sessionUser.getEmail());
        model.addAttribute("myBlog", userBlog );

        //세션 유저가 이 블로그 유저를 팔로우하고 있는지 알아보는 로직 추가

        model.addAttribute("followOrNot",followerService.followingOrNot(sessionUser,blogName));

        if(blogService.getBlogName(sessionUser.getEmail()).equals(blogName)) {
            model.addAttribute("canFollow", false);


        }else {

            model.addAttribute("canFollow",true);


        }
        log.info("myblogName :" + blogService.getBlogName(sessionUser.getEmail()) );
        log.info("blogName :" + blogName );

        User user = userRepository.findByEmail(sessionUser.getEmail()).get();


        Post post = postService.getPostByBlogNameAndUUID(blogName,UUID);

        List<String> tagsTitle = tagMappingService.getTagTitle(blogName,post.getTitle());

        log.info("tagsTitle :" + tagsTitle );

        PostViewDTO postViewDTO = new PostViewDTO(post,postService.getTotalRepleCount(post));

        List<RepleDTO> repleDTOS = repleService.getReples(post,user);

        model.addAttribute("profile",postViewDTO);

        model.addAttribute("reples",repleDTOS);

        model.addAttribute("tags",tagsTitle);

        model.addAttribute("blogName",blogName);

        return "/basic/Post";
    }


    @PostMapping("post/delete")
    public void deletePost(HttpServletRequest request, @RequestBody PostDeleteDTO postDeleteDTO) {

        HttpSession session = request.getSession(false);

        SessionUser sessionuser = (SessionUser) session.getAttribute("user");

        String blogName = blogService.getBlogName(sessionuser.getEmail());

        String uuid = postDeleteDTO.getUuid();

        postService.deletePost(blogName,uuid);


    }



    @Transactional
    @PostMapping("post/create")
    public String createPost(Model model, HttpServletRequest request, @RequestBody PostDTO postDTO) {


        //세션 얻어서 검사

        HttpSession session = request.getSession(false);

        if(session == null ) {
            return "redirect:/";
        }



        // 블로그 얻어옴
        SessionUser sessionuser = (SessionUser) session.getAttribute("user");

        User user = userRepository.findByEmail(sessionuser.getEmail()).get();

        Blog blog = blogRepository.findById(user.getId()).get();



        String title = postDTO.getTitle();

        PostController.log.info(title);

        String content = postDTO.getContent();

        PostController.log.info(content);

        String tags = postDTO.getTag();

        PostController.log.info(tags);

        String seriesTitle = postDTO.getSeries();

        log.info(seriesTitle);

        boolean temp = false;

        if(seriesRepository.findByBlogIdAndTitle(blog,seriesTitle).isPresent()) {
            Series series = seriesRepository.findByBlogIdAndTitle(blog,seriesTitle).get();


            //포스트 만들기

            //임시저장된 다른 포스트가 있나 검사 후에 있으면 그거 false로 바꾸고 재저장.

            Post post = postService.createHasSeriesPost(blog, title, series, temp, content);

            //파쇄해서 태그 만들고 태그 매핑 시킴

            tagMapper(tags,post);


        } else {

            Post post = postService.createNonSeriesPost(blog, title,temp,content);

            tagMapper(tags,post);
        }

        return "redirect:/dabblelog/" + blog.getBlogName();


    }

    @Transactional
    @PostMapping("post/tempCreate")
    public String createTemp(Model model, HttpServletRequest request, @RequestBody PostDTO postDTO) {

        //세션 얻어서 검사

        HttpSession session = request.getSession(false);

        if(session == null ) {
            return "redirect:/";
        }


        SessionUser sessionuser = (SessionUser) session.getAttribute("user");

        User user = userRepository.findByEmail(sessionuser.getEmail()).get();

        Blog blog = blogRepository.findById(user.getId()).get();



        String title = postDTO.getTitle();

        PostController.log.info(title);

        String content = postDTO.getContent();

        PostController.log.info(content);

        String tags = postDTO.getTag();

        PostController.log.info(tags);

        //검사해서 블로그아이디랑 타이틀 같은 거 있으면 업데이트 하기



        Post post = postService.createNonSeriesPost(blog,title,false,content);

        //파쇄해서 태그 만들고 태그 매핑 시킴

        tagMapper(tags,post);



        return "redirect:/dabblelog/" + blog.getBlogName();
    }



    //태그 처리하고 매핑 시키는 메서드

    @Transactional
    public void tagMapper(String tags, Post postId) {

        String[] tagArray = tags.split(" ");


        for(String tagEle : tagArray) {
            PostTag postTag = postTagService.createPostTag(tagEle);
            tagMappingService.createTagMapping(postId, postTag);
        }

        PostController.log.info("태그 매핑 완료");

    }






}