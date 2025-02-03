package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Dabble;
import com.dabblelog.side.domain.dto.DabbleDaysDTO;
import com.dabblelog.side.domain.dto.DabblePostDTO;
import com.dabblelog.side.repository.DabbleRepository;
import com.dabblelog.side.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

        Optional<Dabble> dabble = dabbleRepository.findByDate(now.format(DateTimeFormatter.ofPattern("yyyy-MM")));

        return dabble.orElseGet(() -> dabbleRepository.save(new Dabble(now)));
    }

    public List<DabbleDaysDTO> getDivDays(LocalDateTime now,List<DabblePostDTO> dabblePostDTOS) {
        LocalDateTime start;

        LocalDateTime end;

        LocalDateTime firstDay = now.with(firstDayOfMonth()).with(LocalTime.MIN);

        LocalDateTime lastDay = now.with(lastDayOfMonth()).with(LocalTime.MAX);

        int firstDayOfWeek = firstDay.getDayOfWeek().getValue();

        int lastDayOfWeek = lastDay.getDayOfWeek().getValue();

        if(firstDayOfWeek != 1) {
            int delta = firstDayOfWeek - 1;

            start = firstDay.minusDays(delta);
            end = firstDay.plusDays(35).minusNanos(1);
        } else {
            start = firstDay;

            end = firstDay.plusDays(35).minusNanos(1);

        }


        List<DabbleDaysDTO> list = new ArrayList<>();

        for(int i = 1; i<=35; i++  ){
            list.add(new DabbleDaysDTO(start));
            start.plusDays(1);
        }




        return getDivPosts(list,dabblePostDTOS);

    }

    //리스트 정렬한 뒤에 날짜별로 묶고 그 날짜에 맞는 데이디티오에 집어넣기

    public List<DabbleDaysDTO> getDivPosts(List<DabbleDaysDTO> dabbleDaysDTOS,List<DabblePostDTO> dabblePostDTOS) {

        for(DabbleDaysDTO dabbleDaysDTO : dabbleDaysDTOS) {
            List<DabblePostDTO> dabblePostDTOList = new ArrayList<>();

            for(DabblePostDTO dabblePostDTO : dabblePostDTOS) {

                if(dabbleDaysDTO.getCreatedDay().isEqual(dabblePostDTO.getCreatedDay())) {
                    dabblePostDTOList.add(dabblePostDTO);
                } else if(dabbleDaysDTO.getCreatedDay().isBefore(dabblePostDTO.getCreatedDay())) {

                    break;

                }
            }

            dabbleDaysDTO.updatePostList(dabblePostDTOList);
        }

        return dabbleDaysDTOS;

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

        return postRepository.findAllByBlogIdAndCreatedDayBetweenOrderByCreatedDay(blog,start,end).stream().map(DabblePostDTO::new).toList();
    }

}
