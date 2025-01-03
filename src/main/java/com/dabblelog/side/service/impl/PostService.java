package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Series;
import com.dabblelog.side.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;


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


}
