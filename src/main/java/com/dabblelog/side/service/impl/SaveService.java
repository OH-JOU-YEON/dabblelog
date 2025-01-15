package com.dabblelog.side.service.impl;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.SavesDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    public List<SavesDTO> getSaves(String email) {

        User user = userRepository.findByEmail(email).get();

        Blog blog = blogRepository.findById(user.getId()).get();

        return postRepository.findAllByTempTrueAndBlogId(blog).stream().map(SavesDTO::new).toList();


    }
}
