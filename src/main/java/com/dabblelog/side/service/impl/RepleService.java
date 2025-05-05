package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.RepleDTO;
import com.dabblelog.side.domain.dto.RepleProfileDTO;
import com.dabblelog.side.domain.dto.ReplyDTO;
import com.dabblelog.side.repository.RepleRepository;
import com.dabblelog.side.repository.UserRepository;
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


  private final UserRepository userRepository;


   public RepleProfileDTO getRepleAuthor(String email,Reple reple) {

       User user = userRepository.findByEmail(email).get();

       String blogName = blogService.getBlogName(email);

       return new RepleProfileDTO(user,blogName,reple);

   }





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




}
