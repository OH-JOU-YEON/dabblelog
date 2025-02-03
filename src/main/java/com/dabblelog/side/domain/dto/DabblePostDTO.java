package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Series;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DabblePostDTO {

    private final String title;

    private final String color;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDateTime createdDay;


    public DabblePostDTO(Post post) {

        String title = post.getTitle();

        if(title.length() >19) {
            title = title.substring(0,16) + "..." ;
        }

        this.title = title;

        Series series = post.getSeriesId();

        String color;

        if(series == null) {
            color = "#000000";
        } else{

            color = post.getSeriesId().getColor();
        }


        this.color = color;

        this.createdDay = post.getCreatedDay();
    }


    public DabblePostDTO(int anotherCount, LocalDateTime createdDay) {

        this.title = "그 외 " + anotherCount + "개";

        this.color = "#000000";

        this.createdDay = createdDay;
    }

}
