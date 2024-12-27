package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.PostTag;
import com.dabblelog.side.repository.PostTagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PostTagService {

    @Autowired
    PostTagRepository postTagRepository;



    //태그 생성하는 로직

    //생성 전에 같은 태그가 있는지 검사

    @Transactional
    public PostTag createPostTag(String tagTitle) {

       if(postTagRepository.findByTitle(tagTitle).isPresent()) {
           return postTagRepository.findByTitle(tagTitle).get();
       } else {
           return postTagRepository.save(new PostTag(tagTitle));
       }
    }






}
