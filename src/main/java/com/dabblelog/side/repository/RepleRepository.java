package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepleRepository extends JpaRepository<Reple,Long> {

    Long countByPostId(Post postId);

    List<Reple> findAllByParentRepleAndPostId(Reple reple,Post post);

    List<Reple> findAllByRootRepleAndPostId(Reple reple,Post post);

    Reple findByPostIdAndAuthorAndCreatedDayBetween(Post post, User user, LocalDateTime start,LocalDateTime end);


}
