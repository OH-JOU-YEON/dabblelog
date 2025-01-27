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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepleService {

    @Autowired
    RepleRepository repleRepository;

    @Autowired
    BlogService blogService;

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

    public void createReple(User author, String url, ReplyDTO replyDTO) {

       url = url.replace("https://","");
       String[] parseURL = url.split("/");



    }

}
