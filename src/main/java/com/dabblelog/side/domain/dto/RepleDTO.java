package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class RepleDTO {

    private final String authorName;

    private final String authorProfile;

    private final String yearAndMonth;

    private final String timeAndMinute;

    private final String content;

    private final boolean imRepleWriter;

    private final boolean imPostWriter;


    private final String authorBlog;

    private final String uuid;



    public RepleDTO(Reple reple, String authorBlog, Post post , User user) {



        this.authorName = reple.getAuthor().getName();

        this.authorProfile = reple.getAuthor().getPicture();

        this.yearAndMonth = reple.getCreatedDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.timeAndMinute = reple.getCreatedDay().format(DateTimeFormatter.ofPattern("hh:mm:ss"));

        this.content = reple.getContent();

        this.imRepleWriter = reple.getAuthor() == user;

        this.imPostWriter = post.getBlogId().getUser() == user;

        this.authorBlog = authorBlog;

        this.uuid = reple.getUuid();
    }





}
