package com.dabblelog.side.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DabbleDaysDTO {

    private final int day;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDateTime createdDay;

    private List<DabblePostDTO> dabblePostDTOS;


    public DabbleDaysDTO(LocalDateTime createdDay,List<DabblePostDTO> dabblePostDTOS){

        this.day = createdDay.getDayOfMonth();

        this.createdDay = createdDay;

        if(dabblePostDTOS.size() > 3) {
            int anotherCount = dabblePostDTOS.size() - 2;
            dabblePostDTOS.subList(3, dabblePostDTOS.size()).clear();

            dabblePostDTOS.add(new DabblePostDTO(anotherCount,this.createdDay));

        }

        this.dabblePostDTOS = dabblePostDTOS;

    }

    public DabbleDaysDTO(LocalDateTime createdDay) {

        this.day = createdDay.getDayOfMonth();

        this.createdDay = getCreatedDay();
    }


    public DabbleDaysDTO updatePostList(List<DabblePostDTO> dabblePostDTOS) {
        if(dabblePostDTOS.size() > 3) {
            int anotherCount = dabblePostDTOS.size() - 2;
            dabblePostDTOS.subList(3, dabblePostDTOS.size()).clear();

            dabblePostDTOS.add(new DabblePostDTO(anotherCount,this.createdDay));

        }

        this.dabblePostDTOS = dabblePostDTOS;

        return this;

    }


}
