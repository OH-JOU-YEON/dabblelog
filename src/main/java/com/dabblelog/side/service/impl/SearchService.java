package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.PostTag;
import com.dabblelog.side.domain.TagMapping;
import com.dabblelog.side.domain.dto.getPostHomeDTO;
import com.dabblelog.side.repository.PostTagRepository;
import com.dabblelog.side.repository.RepleRepository;
import com.dabblelog.side.repository.TagMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    @Autowired
    TagMappingRepository tagMappingRepository;

    @Autowired
    PostTagRepository postTagRepository;

    @Autowired
    RepleRepository repleRepository;



    List<Long> getPostTagsId(String keyword) {

        return  postTagRepository.findAllByTitleContaining(keyword).stream().map(PostTag::getId).toList();
    }

    Page<TagMapping> getTagMappings(String keyword) {

        List<Long> tagsIdList = getPostTagsId(keyword);

        return tagMappingRepository.findAllByTagIdIn(tagsIdList);


    }

    Page<getPostHomeDTO> getPostHomeDTOS(String keyword) {



        return getTagMappings(keyword).map(s->new getPostHomeDTO(s.getPostId(),repleRepository.countByPostId(s.getPostId())));
    }


}
