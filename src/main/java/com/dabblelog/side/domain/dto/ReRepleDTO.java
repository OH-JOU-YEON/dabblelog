package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Reple;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ReRepleDTO {

    private final String authorName;

    private final String authorProfile;

    private final String yearAndMonth;

    private final String timeAndMinute;

    private final String content;

    private final String authorBlog;



    public ReRepleDTO(Reple reple,String authorBlog){


        this.authorName = reple.getAuthor().getName();

        this.authorProfile = reple.getAuthor().getPicture();

        this.yearAndMonth = reple.getCreatedDay().format(DateTimeFormatter.ofPattern("yy-MM-dd"));

        this.timeAndMinute = reple.getCreatedDay().format(DateTimeFormatter.ofPattern("hh-mm-ss"));

        this.content = reple.getContent();

        this.authorBlog = authorBlog;

    }
}
