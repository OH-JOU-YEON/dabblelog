package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Series;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class SeriesDTO {

    private final String title;

    private final String color;

    private final Long postCount;

    private final String lastUpdate;

    private final String seriesImage;

    public SeriesDTO(Series series, Long postCount, String seriesImage, LocalDateTime lastUpdate) {

        this.title = series.getTitle();

        this.color = series.getColor();

        this.postCount = postCount;

        this.seriesImage = seriesImage;

        this.lastUpdate = lastUpdate.format(DateTimeFormatter.ofPattern("yy-MM-dd"));
    }
}
