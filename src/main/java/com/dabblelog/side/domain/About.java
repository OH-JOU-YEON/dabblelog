package com.dabblelog.side.domain;

import com.dabblelog.side.domain.dto.AboutDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class About {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(length = 50000)
    private String content;

    private Blog blogId;



    public About(Blog blogId) {

        this.blogId = blogId;
    }

    public About(Blog blog, AboutDTO aboutDTO) {

        this.blogId = blog;

        this.content = aboutDTO.getContent();
    }
}
