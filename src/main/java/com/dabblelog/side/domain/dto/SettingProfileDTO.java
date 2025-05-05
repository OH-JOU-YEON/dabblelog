package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class SettingProfileDTO {

    private final String blogName;

    private final String name;

    private final String readme;

    private final String picture;




    public SettingProfileDTO(User user, String blogName) {

        this.blogName = blogName;

        this.name = user.getName();

        this.picture = user.getPicture();

        this.readme = user.getReadme();


    }
}
