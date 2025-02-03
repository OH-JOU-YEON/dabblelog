package com.dabblelog.side.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostDTO {

    private String title;

    private String tag;

    private String content;

    private String series;
}
