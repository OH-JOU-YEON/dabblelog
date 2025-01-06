package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Favorite;
import com.dabblelog.side.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findAllByPostId(Post postId);

    List<Favorite> findAllByBlogId(Blog blogId);

    Long countByPostId(Post postId);
}
