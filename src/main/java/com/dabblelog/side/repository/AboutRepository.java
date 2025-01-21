package com.dabblelog.side.repository;


import com.dabblelog.side.domain.About;
import com.dabblelog.side.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AboutRepository extends JpaRepository<About,Long> {

    Optional<About> findByBlogId(Blog blogId);
}
