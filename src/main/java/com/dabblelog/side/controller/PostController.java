package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.*;
import com.dabblelog.side.domain.dto.PostDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.SeriesRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.PostService;
import com.dabblelog.side.service.impl.PostTagService;
import com.dabblelog.side.service.impl.TagMappingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
public class PostController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    @Autowired
    SeriesRepository seriesRepository;

    @Autowired
    PostTagService postTagService;

    @Autowired
    TagMappingService tagMappingService;

    //개별 페이지 관련 로직들
    @GetMapping("/post")
    public String getPost(Model model, HttpServletRequest request) {


        return "/basic/Post";
    }





    @Transactional
    @PostMapping("post/create")
    public String createPost(Model model, HttpServletRequest request, @RequestBody PostDTO postDTO) {


        //세션 얻어서 검사

        HttpSession session = request.getSession(false);

        if(session == null ) {
            return "basic/home";
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

        String seriesTitle = postDTO.getSeriesTitle();

        boolean temp = false;

        if(seriesRepository.findByBlogIdAndTitle(blog,seriesTitle).isPresent()) {
            Series series = seriesRepository.findByBlogIdAndTitle(blog,seriesTitle).get();


            //포스트 만들기

            Post post = postService.createHasSeriesPost(blog, title, series, temp, content);

            //파쇄해서 태그 만들고 태그 매핑 시킴

            tagMapper(tags,post);


        }
        else {



            //포스트 만들기

            Post post = postService.createNonSeriesPost(blog,title,temp,content);

            //파쇄해서 태그 만들고 태그 매핑 시킴

            tagMapper(tags,post);

        }
        return "basic/post";


    }

    @Transactional
    @PostMapping("post/tempCreate")
    public String createTemp(Model model, HttpServletRequest request, @RequestBody PostDTO postDTO) {

        //세션 얻어서 검사

        HttpSession session = request.getSession(false);

        if(session == null ) {
            return "basic/home";
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

        Post post = postService.createNonSeriesPost(blog,title,false,content);

        //파쇄해서 태그 만들고 태그 매핑 시킴

        tagMapper(tags,post);



        return "basic/post";
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
