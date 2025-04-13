package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Favorite;
import com.dabblelog.side.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {



    Page<Favorite> findAllByUserId(User userId, Pageable pageable);
}
