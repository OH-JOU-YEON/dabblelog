package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostViewDTO {

    private final String title;

    private final String author;

    private final String seriesTitle;

    private final List<String> tagsTitle;

    private final Long likeCount;

    private final Long repleCount;



    public PostViewDTO(Post post,List<String> tagsTitle, Long repleCount){

        this.title = post.getTitle();

        this.author = post.getBlogId().getUser().getName();

        this.seriesTitle = post.getSeriesId().getTitle();

        this.tagsTitle = tagsTitle;

        this.likeCount = (long) post.getLikeCount();

        this.repleCount = repleCount;
    }
}
