package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Series;
import lombok.Getter;

@Getter
public class getSeriesDTO {

    private final String seriesTitle;

    private final String seriesColor;

    private final String seriesRecentThumbnails;

    private final Long seriesPostCount;




    public getSeriesDTO(Series series, Long seriesPostCount,String seriesRecentThumbnails) {
        this.seriesColor = series.getColor();

        this.seriesTitle = series.getTitle();

        this.seriesRecentThumbnails = seriesRecentThumbnails;

        this.seriesPostCount = seriesPostCount;



    }


}
