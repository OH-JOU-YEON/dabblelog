package com.dabblelog.side.service;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class BlogService {

    @Autowired
   final BlogRepository blogRepository;

    public void createBlog(User user) {

        //이미 블로그가 존재하면 그냥 있음
        if(blogRepository.findByUserId(user).isEmpty()) {
        blogRepository.save(new Blog(user));
        log.info("새 블로그가 생성되었습니다");
        }
        log.info("기존 블로그가 존재하는 사용자입니다");

    }

    //블로그 아이디로 블로그 찾기
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    //유저 아이디로 블로그 찾기
    public Optional<Blog> getBlogByUserId(User user) {
        return blogRepository.findByUserId(user);
    }

    //블로그 삭제하기
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    //블로그 아이디로 유저 찾기
    public User getUserByBlogId(Long id) {
        Blog blog =  blogRepository.findById(id).get();
        return blog.getUserId();
    }


}
