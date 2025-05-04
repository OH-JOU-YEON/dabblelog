package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {



    List<Post> findAllByBlogIdAndSeriesId(Blog blogId, Series seriesId);

    Post findByUuidAndBlogId(String uuid, Blog blogId);

    Page<Post> findAllByBlogIdIn(List<Blog> blogs, Pageable pageable);

    Page<Post> findAllByBlogId(Blog blog,Pageable pageable);

    Long countByBlogIdAndSeriesId(Blog blogId,Series series);

    Post findByBlogIdAndTitle(Blog blogId,String title);


    List<Post> findAllByBlogIdAndCreatedDayBetweenOrderByCreatedDay(Blog blog, LocalDateTime start,LocalDateTime end);




    List<Post> findAllByTempTrueAndBlogId(Blog blog);

    Post findByTitle(String title);

}
