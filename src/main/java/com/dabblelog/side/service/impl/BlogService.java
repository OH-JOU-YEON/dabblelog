package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    //본인 블로그가 존재하는지 하지 않는지 판단 후에 없으면 새 블로그 생성함 검사하는 거 이메일

    @Transactional
    public Blog ifBlogIsNotExistCreateBlog(String email) {

        User user = userRepository.findByEmail(email).get();

        if(blogRepository.findById(user.getId()).isPresent()) {
            return blogRepository.findById(user.getId()).get();
        }else {
            return blogRepository.save(new Blog(user));
        }
    }
}
