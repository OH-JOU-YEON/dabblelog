package com.dabblelog.side.repository;

import com.dabblelog.side.domain.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    Optional<PostTag> findByTitle(String title);

    List<PostTag> findByTitleContaining(String keyword);
}
