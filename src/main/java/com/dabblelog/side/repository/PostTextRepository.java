package com.dabblelog.side.repository;


import com.dabblelog.side.domain.PostText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTextRepository extends JpaRepository<PostText, Long> {


}
