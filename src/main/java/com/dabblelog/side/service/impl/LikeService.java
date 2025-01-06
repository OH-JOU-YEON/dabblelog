package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Favorite;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.repository.FavoriteRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
public class LikeService {

    @Autowired
    FavoriteRepository favoriteRepository;

    public Long getPostLikedCount(Post postId) {

        return favoriteRepository.countByPostId(postId);
    }
}
