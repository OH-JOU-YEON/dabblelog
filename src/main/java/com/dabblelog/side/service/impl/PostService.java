package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.*;
import com.dabblelog.side.domain.dto.getPostHomeDTO;
import com.dabblelog.side.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final FollowerRepository followerRepository;

    @Autowired
    private final BlogRepository blogRepository;

    @Autowired
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

    //포스트 수정



    //시리즈 상세 페이지 띄우는 메서드
    @Transactional
    public List<Post> getSeriesDetails(Blog blog, Series series) {

        return postRepository.findAllByBlogIdAndSeriesId(blog,series);


    }


    public Page<getPostHomeDTO> getPostPageable(Pageable pageable) {
        return postRepository.findAll(pageable).map(post -> new getPostHomeDTO(post,getTotalRepleCount(post)));
    }


    public Page<getPostHomeDTO> getFeed(Pageable pageable, String email) {

        //일단 내가 팔로잉한 목록을 얻자

        User user = userRepository.findByEmail(email).get();

        List<Follower> myFollowing = followerRepository.findAllByFollowingId(user);

        //레포지토리에 검색하기 위해 유저만 뽑는다.

        List<User> myFollowingUser = myFollowing.stream().map(Follower::getFollowedId).toList();

        List<Blog> myFollowingUserBlog = blogRepository.findAllByUser_IdIn(myFollowingUser);

        //시간순서로 나열한다


        return postRepository.findAllByBlogIdIn(myFollowingUserBlog, pageable).map(post -> new getPostHomeDTO(post,getTotalRepleCount(post)));


    }


    public Long getTotalRepleCount(Post postId) {

        return repleRepository.countByPostId(postId);
    }





}
