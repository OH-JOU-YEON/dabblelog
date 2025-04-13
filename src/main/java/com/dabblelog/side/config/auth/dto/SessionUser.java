package com.dabblelog.side.config.auth.dto;


import com.dabblelog.side.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { // 직렬화 기능을 가진 세션 DTO

    // 인증된 사용자 정보만 필요 => name, email 필드만 선언
    private final String name;
    private final String email;


    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}