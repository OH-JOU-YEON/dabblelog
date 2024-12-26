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

    @Transactional
    public PostTag createPostTag(String tagTitle) {
        return postTagRepository.save(new PostTag(tagTitle));
    }



}
