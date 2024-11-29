package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Reple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepleRepository extends JpaRepository<Reple,Long> {


}
