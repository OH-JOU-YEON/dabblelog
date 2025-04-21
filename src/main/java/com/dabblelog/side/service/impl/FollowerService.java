package com.dabblelog.side.service.impl;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Follower;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.FollowerRepository;
import com.dabblelog.side.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowerService {


   private final FollowerRepository followerRepository;


   private final UserRepository userRepository;


   private final BlogRepository blogRepository;

   private final BlogService blogService;



    //새로 팔로우하는 로직
    @Transactional
    public Follower createFollower(SessionUser user, String blogName) {

        User followingUser = userRepository.findByEmail(user.getEmail()).get();

        User followedUser = blogRepository.findByBlogName(blogName).get().getUser();

        Optional<Follower> follower = followerRepository.findByFollowingIdAndFollowedId(followingUser,followingUser);

        return follower.orElseGet(() -> followerRepository.save(new Follower(followingUser, followedUser)));


    }

    //팔로우 취소하는 로직
    public void deleteFollower(SessionUser user, String blogName) {

        User followingUser = userRepository.findByEmail(user.getEmail()).get();

        User followedUser = blogRepository.findByBlogName(blogName).get().getUser();

        followerRepository.delete(followerRepository.findByFollowingIdAndFollowedId(followingUser,followedUser).get());

    }

    //블로그 검색할 때 그 블로그가 내가 팔로우하는 블로그인지 아닌지 찾아보는 로직도 추가해야 될 듯
    public String followingOrNot(SessionUser user, String blogName) {

        User followingUser = userRepository.findByEmail(user.getEmail()).get();

        //블로그 이름으로 그 블로그 유저 가져오기

        User followedUser = userRepository.findById(blogService.getBlogByName(blogName).getUser().getId()).get();

        if(followerRepository.findByFollowingIdAndFollowedId(followingUser,followedUser).isPresent()) {
            return "팔로잉";
        }else {
          return "팔로우";
        }
    }
}
