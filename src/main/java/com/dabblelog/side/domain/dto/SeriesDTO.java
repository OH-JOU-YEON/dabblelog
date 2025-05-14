package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Series;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class SeriesDTO {

    private final String seriesTitle;

    private final String seriesColor;

    private final String seriesRecentThumbnails;

    private final Long seriesPostCount;

    private final String uuid;




    public SeriesDTO(Series series, Long seriesPostCount, String seriesRecentThumbnails) {
        this.seriesColor = series.getColor();

        this.seriesTitle = series.getTitle();

        String temp = seriesRecentThumbnails;

        if(temp.isEmpty()) {
            temp = "/img/pencil-square.svg";
        }


        this.seriesRecentThumbnails = temp;


        this.seriesPostCount = seriesPostCount;

        this.uuid = series.getUuid();





    }


}
