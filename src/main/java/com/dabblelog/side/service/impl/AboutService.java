package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.About;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.dto.AboutDTO;
import com.dabblelog.side.repository.AboutRepository;
import com.dabblelog.side.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutService {


   private final AboutRepository aboutRepository;


   private final BlogRepository blogRepository;

    public About getAboutByBlog(Blog blog, AboutDTO aboutDTO) {
        if(aboutRepository.findByBlogId(blog).isPresent()) {

            return aboutRepository.findByBlogId(blog).get();

        } else {

           return aboutRepository.save(new About(blog,aboutDTO));

        }

    }

    public boolean isAboutExist(String blogName) {

        Blog blog = blogRepository.findByBlogName(blogName).get();

        return aboutRepository.findByBlogId(blog).isPresent();
    }

    public About getAboutByBlogName(String blogName) {

        Blog blog = blogRepository.findByBlogName(blogName).get();

        return aboutRepository.findByBlogId(blog).get();
    }

}
