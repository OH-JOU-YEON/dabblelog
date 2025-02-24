package com.dabblelog.side.domain;

import com.dabblelog.side.domain.dto.AboutDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(length = 50000)
    private String content;

    @ManyToOne
    private Blog blogId;



    public About(Blog blogId) {

        this.blogId = blogId;
    }

    public About(Blog blog, AboutDTO aboutDTO) {

        this.blogId = blog;

        this.content = aboutDTO.getContent();
    }
}
