package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Favorite;
import com.dabblelog.side.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findAllByPostId(Post postId);


    Long countByPostId(Post postId);
}
