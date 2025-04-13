package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.User;
import lombok.Getter;

@Getter
public class BlogProfileDTO {

    private final String profileImg;

    private final String nickName;

    private final String readme;

    private final Long following;

    private final Long follower;

    public BlogProfileDTO(User user, Long following, Long follower) {

       String profileImg1 = user.getPicture();

        if(profileImg1.isEmpty()) {
            profileImg1 = "/img/person-circle.svg";
        }

        this.profileImg = profileImg1;
        this.nickName = user.getName();

        this.readme = user.getReadme();

        this.following = following;

        this.follower = follower;
    }
}
