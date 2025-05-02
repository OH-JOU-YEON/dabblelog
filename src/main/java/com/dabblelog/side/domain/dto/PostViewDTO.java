package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Series;
import lombok.Getter;

import java.util.List;

@Getter
public class PostViewDTO {

    private final String title;

    private final String author;

    private final String seriesTitle;



    private final Long likeCount;

    private final Long repleCount;

    private final String content;



    public PostViewDTO(Post post, Long repleCount){

        this.title = post.getTitle();

        this.author = post.getBlogId().getUser().getName();

        Series series = post.getSeriesId();
        String seriesTitle;

        if(series == null) {
            seriesTitle = "";
        } else {

            seriesTitle = post.getSeriesId().getTitle();
        }



        if(seriesTitle == null) {
            seriesTitle = "";
        }

        this.content = post.getContent();

        this.seriesTitle = seriesTitle;



        this.likeCount = (long) post.getLikeCount();

        this.repleCount = repleCount;
    }
}
