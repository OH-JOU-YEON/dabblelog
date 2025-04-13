package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Dabble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DabbleRepository extends JpaRepository<Dabble, Long> {

    Optional<Dabble> findByDate(String date);
}
