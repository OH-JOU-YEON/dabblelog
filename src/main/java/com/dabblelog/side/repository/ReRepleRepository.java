package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.ReReple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReRepleRepository extends JpaRepository<ReReple, Long> {

    Long countByPostId(Post postId);
}
