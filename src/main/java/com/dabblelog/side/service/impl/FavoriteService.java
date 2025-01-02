package com.dabblelog.side.service.impl;


import com.dabblelog.side.repository.FavoriteRepository;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    //똑같이 좋아요 누르면 하트 빨간색으로 바뀌고 세션 유저하고 포스트 이름? 받아와서 매핑함

    //이거는 dto 만들어서 던져야될듯 ㅇㅋ


}
