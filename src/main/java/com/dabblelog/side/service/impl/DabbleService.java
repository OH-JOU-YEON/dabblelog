package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Dabble;
import com.dabblelog.side.repository.DabbleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DabbleService {

    @Autowired
    DabbleRepository dabbleRepository;

    public Dabble getDabbleByDate(LocalDateTime now) {

        Optional<Dabble> dabble = dabbleRepository.findByYearAndMonth(now.format(DateTimeFormatter.ofPattern("yy-MM")));

        return dabble.orElseGet(() -> dabbleRepository.save(new Dabble(now)));
    }


}
