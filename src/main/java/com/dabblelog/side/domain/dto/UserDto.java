package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserDto {

    private String name;


    private String email;


    private String readme;


    private String picture;

    public UserDto(User user) {
        this.name = user.getName();

        this.email= user.getEmail();

        this.readme = user.getReadme();

        this.picture = user.getPicture();

    }
}
