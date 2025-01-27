package com.dabblelog.side.domain.dto;


import lombok.Getter;

@Getter
public class ReplyDTO {

    private String content;

    private String url;

    private String parentDay;

    private String parentTime;

    private String parentBlogURL;
}
