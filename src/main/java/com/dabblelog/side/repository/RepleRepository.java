package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepleRepository extends JpaRepository<Reple,Long> {

    Long countByPostId(Post postId);

    List<Reple> findAllByPostId(Post post);

    Optional<Reple> findByPostIdAndUuid(Post postId,String uuid);



}
