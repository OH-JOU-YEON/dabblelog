package com.dabblelog.side.repository;

import com.dabblelog.side.domain.TagMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMappingRepository extends JpaRepository<TagMapping, Long> {


}
