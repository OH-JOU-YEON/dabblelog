package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.RepleDTO;
import com.dabblelog.side.domain.dto.ReplyDTO;
import com.dabblelog.side.repository.RepleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepleService {


   private final RepleRepository repleRepository;


   private final BlogService blogService;


   private final PostService postService;





   public List<RepleDTO> getReples(Post post){

        List<Reple> repleList = repleRepository.findAllByPostId(post);


       return repleList.stream().map(s -> new RepleDTO(s,blogService.getRepleAuthor(s).getBlogName())).toList();
    }


    public String getAuthorBlog(Reple reple) {

        User user = reple.getAuthor();

        return "/dabblelog/" + blogService.getRepleAuthor(reple).getBlogName();

    }


    public User getUserByURL(String url) {

       String[] parseURL = url.split("/");

       return blogService.getUserByBlogName(parseURL[2]);
    }


    public Reple createReple( ReplyDTO replyDTO) {

        User author = getUserByURL(replyDTO.getUrl());

        Post post = postService.getPostIdByURL(replyDTO.getParentBlogURL());

        LocalDateTime createdDay = LocalDateTime.parse(replyDTO.getParentDay() + " " + replyDTO.getParentTime());


        return repleRepository.save(new Reple(post,author,LocalDateTime.now(),replyDTO.getContent()));


    }

}
