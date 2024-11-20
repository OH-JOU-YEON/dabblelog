package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Dabble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DabbleRepository extends JpaRepository<Dabble, Long> {
}
