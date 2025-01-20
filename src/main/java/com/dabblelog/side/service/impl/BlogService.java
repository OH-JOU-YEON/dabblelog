package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.getBlogPostDTO;
import com.dabblelog.side.domain.dto.getBlogProfileDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.FollowerRepository;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    FollowerRepository followerRepository;

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

    public Page<getBlogPostDTO> getBlogPostDTOS(String blogName, Pageable pageable){

        Blog blog = blogRepository.findByBlogName(blogName).get();
        return postRepository.findAllByBlogId(blog,pageable).map(getBlogPostDTO::new);
    }

    public getBlogProfileDTO getBlogProfileDTO(String blogName) {
        Blog blog = blogRepository.findByBlogName(blogName).get();

        User blogUser = blog.getUser();

        return new getBlogProfileDTO(blogUser,followerRepository.countByFollowingId(blogUser),followerRepository.countByFollowedId(blogUser));

    }
}
