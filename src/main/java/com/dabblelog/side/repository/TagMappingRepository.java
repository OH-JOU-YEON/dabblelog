package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.PostTag;
import com.dabblelog.side.domain.TagMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagMappingRepository extends JpaRepository<TagMapping, Long> {

    List<TagMapping> findAllByPostId(Post postId);

    Optional<TagMapping> findByPostIdAndTagId(Post postId, PostTag tagId);


}
