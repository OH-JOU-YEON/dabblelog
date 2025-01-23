package com.dabblelog.side.service.impl;


import com.dabblelog.side.repository.RepleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepleService {

    @Autowired
    RepleRepository repleRepository;


}
