package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Series;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class getSeriesDTO {

    private final String seriesTitle;

    private final String seriesColor;

    private final String seriesRecentThumbnails;

    private final Long seriesPostCount;




    public getSeriesDTO(Series series, Long seriesPostCount,String seriesRecentThumbnails) {
        this.seriesColor = series.getColor();
        log.info("seriesDTO.seriesColor = {}", this.seriesColor);

        this.seriesTitle = series.getTitle();

        log.info("seriesDTO.seriesTitle = {}", this.seriesTitle);

        this.seriesRecentThumbnails = seriesRecentThumbnails;

        log.info("seriesDTO.seriesThumbnails = {}", this.seriesRecentThumbnails);

        this.seriesPostCount = seriesPostCount;

        log.info("seriesDTO.seriesPostCount = {}", this.seriesPostCount);



    }


}
