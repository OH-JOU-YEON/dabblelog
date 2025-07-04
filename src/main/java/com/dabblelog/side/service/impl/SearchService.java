package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.PostTag;
import com.dabblelog.side.domain.TagMapping;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.repository.PostTagRepository;
import com.dabblelog.side.repository.RepleRepository;
import com.dabblelog.side.repository.TagMappingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {


   private final TagMappingRepository tagMappingRepository;


   private final PostTagRepository postTagRepository;


   private final RepleRepository repleRepository;



   List<Long> getPostTagsId(String keyword) {

        return  postTagRepository.findAllByTitleContaining(keyword).stream().map(PostTag::getId).toList();
    }

    Page<TagMapping> getTagMappings(String keyword, Pageable pageable) {

        List<Long> tagsIdList = getPostTagsId(keyword);

        return tagMappingRepository.findAllByTagIdIn(tagsIdList,pageable);


    }

    public Page<PostHomeDTO> getPostHomeDTOS(String keyword, Pageable pageable) {



        return getTagMappings(keyword,pageable).map(s->new PostHomeDTO(s.getPostId(),repleRepository.countByPostId(s.getPostId())));
    }


}
