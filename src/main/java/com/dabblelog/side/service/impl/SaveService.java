package com.dabblelog.side.service.impl;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Series;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.domain.dto.SavesDTO;
import com.dabblelog.side.domain.dto.SavesDetailsDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveService {


   private final PostRepository postRepository;


   private final BlogRepository blogRepository;


   private final UserRepository userRepository;


   private final PostTagService postTagService;

    public List<SavesDTO> getSaves(String email) {

        User user = userRepository.findByEmail(email).get();

        Blog blog = blogRepository.findById(user.getId()).get();

        return postRepository.findAllByTempTrueAndBlogId(blog).stream().map(SavesDTO::new).toList();


    }

    public SavesDetailsDTO getSavesDetails(String uuid,Blog blog) {

        Post post = postRepository.findByUuidAndBlogId(uuid,blog);

        String tags = postTagService.getTags(post);

        postTagService.deletePostTags(post);

      return new SavesDetailsDTO(post, tags);
    }


    //시리즈

}
