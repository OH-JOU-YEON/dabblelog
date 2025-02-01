package com.dabblelog.side.domain.dto;


import lombok.Getter;

import java.util.List;

@Getter
public class DabbleDTO {

    private String year;

    private String month;

    private List<DabblePostDTO> dabblePosts;
}
