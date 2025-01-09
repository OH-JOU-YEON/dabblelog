package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Series;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class getSeriesDTO {

    private final String seriesTitle;

    private final String seriesColor;

    private final String seriesRecentThumbnails;

    private final Long seriesPostCount;

    private final String seriesLastUpdateDay;


    public getSeriesDTO(Series series, Long seriesPostCount,String seriesRecentThumbnails, LocalDateTime lastUpdateDay) {
        this.seriesColor = series.getColor();

        this.seriesTitle = series.getTitle();

        this.seriesRecentThumbnails = seriesRecentThumbnails;

        this.seriesPostCount = seriesPostCount;

        this.seriesLastUpdateDay = lastUpdateDay.format(DateTimeFormatter.ofPattern("yy-MM-dd"));

    }


}
