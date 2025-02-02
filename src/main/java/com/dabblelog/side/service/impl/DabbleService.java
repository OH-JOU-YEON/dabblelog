package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Dabble;
import com.dabblelog.side.domain.dto.DabblePostDTO;
import com.dabblelog.side.repository.DabbleRepository;
import com.dabblelog.side.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
@RequiredArgsConstructor
@Slf4j
public class DabbleService {

    @Autowired
    DabbleRepository dabbleRepository;

    @Autowired
    PostRepository postRepository;

    public Dabble getDabbleByDate(LocalDateTime now) {

        Optional<Dabble> dabble = dabbleRepository.findByYearAndMonth(now.format(DateTimeFormatter.ofPattern("yy-MM")));

        return dabble.orElseGet(() -> dabbleRepository.save(new Dabble(now)));
    }

    public List<Integer> getDivDays(LocalDateTime now) {
        LocalDateTime start;

        LocalDateTime end;

        LocalDateTime firstDay = now.with(firstDayOfMonth()).with(LocalTime.MIN);

        LocalDateTime lastDay = now.with(lastDayOfMonth()).with(LocalTime.MAX);

        int firstDayOfWeek = firstDay.getDayOfWeek().getValue();

        int lastDayOfWeek = lastDay.getDayOfWeek().getValue();

        if(firstDayOfWeek != 1) {
            int delta = firstDayOfWeek - 1;

            start = firstDay.minusDays(delta);
            end = firstDay.plusDays(34);
        } else {
            start = firstDay;

            end = firstDay.plusDays(34);

        }


        List<Integer> list = new ArrayList<>();

        for(int i = 1; i<=35; i++  ){
            list.add(start.getDayOfMonth());
            start.plusDays(1);
        }

        return list;

    }


    public List<DabblePostDTO> getDabblePostDTOs(LocalDateTime now, Blog blog) {

        LocalDateTime start;

        LocalDateTime end;

        LocalDateTime firstDay = now.with(firstDayOfMonth()).with(LocalTime.MIN);

        LocalDateTime lastDay = now.with(lastDayOfMonth()).with(LocalTime.MAX);

        int firstDayOfWeek = firstDay.getDayOfWeek().getValue();

        int lastDayOfWeek = lastDay.getDayOfWeek().getValue();

        if(firstDayOfWeek != 1) {
            int delta = firstDayOfWeek - 1;

            start = firstDay.minusDays(delta);
             end = lastDay.minusDays(delta);
        } else {
            start = firstDay;

            end = lastDay;

        }

        return postRepository.findAllByBlogIdAndCreatedDayBetween(blog,start,end).stream().map(DabblePostDTO::new).toList();
    }

}
