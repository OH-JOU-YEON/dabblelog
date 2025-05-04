package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.*;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {


    private final PostRepository postRepository;


    private final UserRepository userRepository;


    private final FollowerRepository followerRepository;


    private final BlogRepository blogRepository;


    private  final RepleRepository repleRepository;




    //포스트 디비 생성

    //시리즈를 갖고 있는 경우

    @Transactional
    public Post createNonSeriesPost(Blog blog, String title, boolean temp, String content) {

        return postRepository.save(new Post(blog,title,temp, content ));
    }

    //시리즈가 없는 경우
    @Transactional
    public Post createHasSeriesPost(Blog blog, String title, Series series, boolean temp, String content) {

        return postRepository.save(new Post(blog,title,series,temp, content));
    }



    public Post getPostByBlogNameAndUUID(String blogName,String UUID) {

        log.info("getPostByBlogNameAndTitle.blogName : " + blogName);
        log.info("getPostByBlogNameAndTitle.postTitle : " + UUID);

        Blog blog = blogRepository.findByBlogName(blogName).get();

        return postRepository.findByUuidAndBlogId(UUID,blog);
    }

    public Post getPostIdByURL(String url) {

        log.info("getPostIdByURL.URL : " + url);
        String[] parseURL = url.split("/");

        log.info("getPostIdByUrl.parseURL[4] : " + parseURL[4]);
        log.info("getPostIdByURL.parseURL[5] : "+ parseURL[5] );





       String url5 = URLDecoder.decode(parseURL[5], StandardCharsets.UTF_8);

      url5 = url5.replace("?","a");

        return getPostByBlogNameAndUUID(parseURL[4],url5);

    }






    public Page<PostHomeDTO> getPostPageable(Pageable pageable) {
        return postRepository.findAll(pageable).map(post -> new PostHomeDTO(post,getTotalRepleCount(post)));
    }


    public Page<PostHomeDTO> getFeed(Pageable pageable, String email) {

        //일단 내가 팔로잉한 목록을 얻자

        User user = userRepository.findByEmail(email).get();

        List<Follower> myFollowing = followerRepository.findAllByFollowingId(user);

        //레포지토리에 검색하기 위해 유저만 뽑는다.

        List<User> myFollowingUser = myFollowing.stream().map(Follower::getFollowedId).toList();

        List<Blog> myFollowingUserBlog = blogRepository.findAllByUser_IdIn(myFollowingUser);

        //시간순서로 나열한다


        return postRepository.findAllByBlogIdIn(myFollowingUserBlog, pageable).map(post -> new PostHomeDTO(post,getTotalRepleCount(post)));


    }


    public Long getTotalRepleCount(Post postId) {

        return repleRepository.countByPostId(postId);
    }


    public void deletePost(String blogName, String postTitle) {

        Blog blog = blogRepository.findByBlogName(blogName).get();



        Post post = postRepository.findByBlogIdAndTitle(blog,postTitle);

        postRepository.delete(post);
    }




}
