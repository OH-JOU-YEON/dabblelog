package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Dabble;
import lombok.Getter;

import java.util.List;

@Getter
public class DabbleDTO {

    private final String year;

    private final String month;

    private final List<DabblePostDTO> dabblePosts;

    public DabbleDTO(Dabble dabble,List<DabblePostDTO> dabblePostDTOS) {

        String[] dates = dabble.getYearAndMonth().split("-");

        this.year = dates[0];

        this.month = dates[1];

        this.dabblePosts = dabblePostDTOS;
    }
}
