package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.dto.RepleDTO;
import com.dabblelog.side.repository.RepleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepleService {

    @Autowired
    RepleRepository repleRepository;

    public List<RepleDTO> getReples(Post post) {
        List<Reple> reples = repleRepository.findAllByPostId(post);


    }
}
