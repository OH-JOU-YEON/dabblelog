package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.About;
import lombok.Getter;

@Getter
public class AboutDTO {

    private String content;


    public AboutDTO() {}


    public AboutDTO(About about) {

        this.content = about.getContent();
    }

    public AboutDTO(String content) {
        this.content = content;
    }
}
