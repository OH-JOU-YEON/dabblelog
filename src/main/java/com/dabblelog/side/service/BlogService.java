package com.dabblelog.side.service;

import com.dabblelog.side.domain.Blog;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {

    //생성

    public Blog createBlog(Long userId);

    //수정

    //블로그 이름 수정

    public void updateBlogName(String blogName);

    //삭제

    public void deleteBlog(Long blogId);


}
