package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class getPostHomeDTO {

    private final String title;


    //미리보기
    private final String preview;

    private final String createdDay;

    private final Long totalRepleCount;

    private final int likeCount;

    private final String thumbnail;


    public getPostHomeDTO(Post post, Long totalRepleCount) {

        this.title = post.getTitle();

        this.preview = getPreview(post.getContent());

        this.createdDay = post.getCreatedDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.likeCount = post.getLikeCount();

        this.thumbnail = "temp";

        this.totalRepleCount = totalRepleCount;




    }

    static String getPreview(String postContent) {
       String subStringContent = postContent.substring(0,31);

       String[] previewArray = subStringContent.split("[<>]");

       return previewArray[1];
    }


}
