package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Dabble;
import lombok.Getter;

import java.util.List;

@Getter
public class DabbleDTO {

    private final String year;

    private final String month;

    private final List<DabbleDaysDTO> dabbleDaysDTOS;

    public DabbleDTO(Dabble dabble,List<DabbleDaysDTO> dabbleDaysDTOS) {

        String[] dates = dabble.getDate().split("-");

        this.year = dates[0];

        this.month = dates[1];

        this.dabbleDaysDTOS = dabbleDaysDTOS;
    }
}
