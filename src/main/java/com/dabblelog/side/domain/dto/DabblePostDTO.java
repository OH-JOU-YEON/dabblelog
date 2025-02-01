package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Post;
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

        this.title = post.getTitle();

        this.color = post.getSeriesId().getColor();

        this.createdDay = post.getCreatedDay();
    }


}
