package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Series;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {

    Optional<Series> findByTitle(String title);

    List<Series> findAllByBlogId(Blog blogId);

    List<Series> findAllByBLogId(Blog blogId, Pageable pageable);

    Optional<Series> findByBlogIdAndTitle(Blog blogId, String title);
}
