package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.BlogPostDTO;
import com.dabblelog.side.domain.dto.BlogProfileDTO;
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

    public Blog getBlogByEmail(String email) {
        User user = userRepository.findByEmail(email).get();

        return blogRepository.findById(user.getId()).get();
    }


    public String getBlogName(String email) {
        User user = userRepository.findByEmail(email).get();
        Blog blog = blogRepository.findById(user.getId()).get();

        return blog.getBlogName();
    }

    public User getUserByBlogName(String blogName) {

        return blogRepository.findByBlogName(blogName).get().getUser();
    }

    public Blog getBlogByName(String blogName) {
        return blogRepository.findByBlogName(blogName).get();
    }

    public Page<BlogPostDTO> getBlogPostDTOS(String blogName, Pageable pageable){

        Blog blog = blogRepository.findByBlogName(blogName).get();
        return postRepository.findAllByBlogId(blog,pageable).map(BlogPostDTO::new);
    }

    public BlogProfileDTO getBlogProfileDTO(String blogName) {
        Blog blog = blogRepository.findByBlogName(blogName).get();

        User blogUser = blog.getUser();

        return new BlogProfileDTO(blogUser,followerRepository.countByFollowingId(blogUser),followerRepository.countByFollowedId(blogUser));

    }

    public Blog getRepleAuthor(Reple reple) {
        return blogRepository.findById(reple.getAuthor().getId()).get();
    }
}
