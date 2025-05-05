package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class RepleProfileDTO {

    //리플 디비에서 생성하고 이걸 전달해줘야 창에서 댓글에 프로필 이미지 넣기 가능

    private final String picture;

    private final String name;

    private final String authorBlog;

    private final String blogURL;

    private final String monthYear;

    private final String time;


    public RepleProfileDTO(User user,String blogName, Reple reple) {

        this.picture = user.getPicture();

        this.name = user.getName();

        this.authorBlog = blogName;

        this.blogURL = "/dabblelog/" + blogName;

        this.monthYear = reple.getCreatedDay().format(DateTimeFormatter.ofPattern("yyyy-mm"));

        this.time = reple.getCreatedDay().format(DateTimeFormatter.ofPattern("HH-mm"));
    }
}
