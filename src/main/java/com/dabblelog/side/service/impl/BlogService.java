package com.dabblelog.side.service.impl;


import com.dabblelog.side.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    //
}
