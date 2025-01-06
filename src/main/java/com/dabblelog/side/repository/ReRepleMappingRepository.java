package com.dabblelog.side.repository;


import com.dabblelog.side.domain.ReRepleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReRepleMappingRepository extends JpaRepository<ReRepleMapping, Long> {

}
