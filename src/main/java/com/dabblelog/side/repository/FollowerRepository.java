package com.dabblelog.side.repository;

import com.dabblelog.side.domain.Follower;
import com.dabblelog.side.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    Optional<Follower> findByFollowingIdAndFollowedId(User followingUser,User followedUser);

    Long countByFollowingId(User followingUser);

    Long countByFollowedId(User followedUser);

    List<Follower> findAllByFollowingId(User followingUser);

}
