package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query("SELECT u FROM Blog u WHERE u.user_id = id")
    Optional<Blog> findByUserId(@Param("id") Long id);
}
