package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findAllByBlogIdAndSeriesId(Blog blogId, Series seriesId);


}
