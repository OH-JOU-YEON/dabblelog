package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
