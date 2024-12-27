package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.PostTag;
import com.dabblelog.side.domain.TagMapping;
import com.dabblelog.side.repository.TagMappingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagMappingService {

    @Autowired
    TagMappingRepository tagMappingRepository;

    //태그 매핑 생성하는 로직

    public TagMapping createTagMapping(Post postId, PostTag tagId) {
        return tagMappingRepository.save(new TagMapping(tagId, postId));
    }





    //매핑 삭제하는 로직

    @Transactional
    public void deleteTagMapping(Post postId, PostTag tagId) {
        TagMapping tagMapping = tagMappingRepository.findByPostIdAndTagId(postId, tagId).get();

        tagMappingRepository.delete(tagMapping);



    }
}
