package com.dabblelog.side.repository;

import com.dabblelog.side.domain.BigDabble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigDabbleRepository extends JpaRepository<BigDabble, Long> {
}
