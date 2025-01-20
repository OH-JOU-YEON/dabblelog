package com.dabblelog.side.domain.dto;

import lombok.Getter;

@Getter
public class getBlogProfileDTO {

    private String profileImg;

    private String nickName;

    private String readme;

    private Long following;

    private Long follower;
}
