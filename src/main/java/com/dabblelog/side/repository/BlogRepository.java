package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    Optional<Blog> findByBlogName(String BlogName);

    List<Blog> findAllByUser_IdIn(List<User> users);


}
