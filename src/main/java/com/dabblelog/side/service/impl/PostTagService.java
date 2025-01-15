package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.PostTag;
import com.dabblelog.side.domain.TagMapping;
import com.dabblelog.side.repository.PostTagRepository;
import com.dabblelog.side.repository.TagMappingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostTagService {

    @Autowired
    PostTagRepository postTagRepository;

    @Autowired
    TagMappingRepository tagMappingRepository;



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


    public String getTags(Post post) {
        List<TagMapping> tagMappings = tagMappingRepository.findAllByPostId(post);

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i<tagMappings.size(); i++) {
            stringBuilder.append(" ").append(tagMappings.indexOf(i));
        }

        return stringBuilder.toString();
    }




}
