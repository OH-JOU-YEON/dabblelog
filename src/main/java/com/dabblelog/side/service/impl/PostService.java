package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.*;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.FollowerRepository;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.UserRepository;
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


    public Page<Post> getPostPageable(Pageable pageable) {
        return postRepository.findAll(pageable);
    }


    public Page<Post> getFeed(Pageable pageable, String email) {

        //일단 내가 팔로잉한 목록을 얻자

        User user = userRepository.findByEmail(email).get();

        List<Follower> myFollowing = followerRepository.findAllByFollowingId(user);

        //레포지토리에 검색하기 위해 유저만 뽑는다.

        List<User> myFollowingUser = myFollowing.stream().map(Follower::getFollowedId).toList();

        List<Blog> myFollowingUserBlog = blogRepository.findAllByUser_IdIn(myFollowingUser);

        //시간순서로 나열한다


        return postRepository.findAllByBlogIdIn(myFollowingUserBlog, pageable);


    }

    static String getPreview(String postContent) {
        String[] splitPostContentWithTagStart = postContent.split("<");



        for(String splitContent : splitPostContentWithTagStart) {
            if(splitContent.contains("img>")) {
                continue;
            }
            else {
                String[] splitPostContentWithTagEnd = splitContent.split(">");
                return splitPostContentWithTagEnd[1];
            }
        }

        //<이거로 파싱해서 img>가 속해있으면 제끼고 아니면 그거를 또 >로 파싱한 뒤에 1번째 거 갖고오면 댐 끝까지 이미지뿐이면 빈 문자열 반환


        return "";

    }

    static String getThumbnails(String postContent) {
        String[] splitPostContentWithTagStart = postContent.split("<");

        //위 메서드와 로직 반대. img> 포함하면 공백으로 잘라서 검사 후에 src를 포함하면 =로 자르고 뒤에 거 반환

        for(String splitContent : splitPostContentWithTagStart) {
            if(splitContent.contains("img>")) {

                String[] splitContentWithBlank = splitContent.split(" ");

                for(String splitWithBlankContent : splitContentWithBlank) {
                    if(splitWithBlankContent.contains("src")) {
                        String[] splitContentWithSrc = splitWithBlankContent.split("=");
                        return splitContentWithSrc[1];
                    }
                    else {
                        continue;
                    }
                }

            }
            else {
                continue;
            }
        }


        return "";

    }


}
