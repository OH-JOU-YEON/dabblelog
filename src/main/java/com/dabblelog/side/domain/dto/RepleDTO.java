package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Reple;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class RepleDTO {

    private final String authorName;

    private final String authorProfile;

    private final String yearAndMonth;

    private final String timeAndMinute;

    private final String content;

    private final List<ReRepleDTO> reRepleDTOS;

    private String authorBlog;

    public RepleDTO(Reple reple,List<ReRepleDTO> reRepleDTOS,String authorBlog) {

        this.authorName = reple.getAuthor().getName();

        this.authorProfile = reple.getAuthor().getPicture();

        this.yearAndMonth = reple.getCreatedDay().format(DateTimeFormatter.ofPattern("yy-MM"));

        this.timeAndMinute = reple.getCreatedDay().format(DateTimeFormatter.ofPattern("dd-h-mm"));

        this.content = reple.getContent();

        this.reRepleDTOS = reRepleDTOS;

        this.authorBlog = authorBlog;
    }


}
