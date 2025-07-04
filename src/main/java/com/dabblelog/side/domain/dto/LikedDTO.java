package com.dabblelog.side.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LikedDTO {

    private Long likeCount;

    private String url;

    private String uuid;
}
