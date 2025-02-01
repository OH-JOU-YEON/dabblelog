package com.dabblelog.side.domain.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DabblePostDTO {

    private String title;

    private String color;

    private LocalDateTime createdDay; 


}
