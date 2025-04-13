package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Post;
import lombok.Getter;

@Getter
public class SavesDetailsDTO {

    private final String title;

    private final String tags;

    private final String content;


    public SavesDetailsDTO(Post post,String tags) {
        this.title = post.getTitle();

        this.tags = tags;

        this.content = post.getContent();
    }
}
