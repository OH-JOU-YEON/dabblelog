package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.ReRepleDTO;
import com.dabblelog.side.domain.dto.RepleDTO;
import com.dabblelog.side.domain.dto.ReplyDTO;
import com.dabblelog.side.repository.RepleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepleService {


    RepleRepository repleRepository;


    BlogService blogService;


    PostService postService;





   public List<RepleDTO> getReples(Post post){

        List<Reple> repleList = repleRepository.findAllByParentRepleAndPostId(null,post);
        List<RepleDTO> reples = new ArrayList<>();

        for(Reple re : repleList) {

            List<ReRepleDTO> reRepleDTOS = repleRepository.findAllByRootRepleAndPostId(re,post).stream().map(s->new ReRepleDTO(s,getAuthorBlog(s))).toList();

            reples.add(new RepleDTO(re,reRepleDTOS,getAuthorBlog(re)));
        }

        return reples;
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

        Reple parentReple = repleRepository.findByPostIdAndAuthorAndCreatedDayBetween(post,author,createdDay,createdDay.plusSeconds(1));

        return repleRepository.save(new Reple(post,author,parentReple,LocalDateTime.now(),replyDTO.getContent()));


    }

}
