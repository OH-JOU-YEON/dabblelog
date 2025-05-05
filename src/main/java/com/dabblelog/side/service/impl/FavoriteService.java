package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Favorite;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.repository.FavoriteRepository;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.RepleRepository;
import com.dabblelog.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {


   private final UserRepository userRepository;

   private final FavoriteRepository favoriteRepository;


   private final RepleRepository repleRepository;

    public Page<PostHomeDTO> getLikedPages(String email, Pageable pageable) {

        User user = userRepository.findByEmail(email).get();

        return favoriteRepository.findAllByUserId(user,pageable).map(s -> new PostHomeDTO(s.getPostId(),repleRepository.countByPostId(s.getPostId())));

    }


    //좋아요 매핑하는 메서드
    public Favorite likeMapping(User user, Post post) {

        return favoriteRepository.save(new Favorite(user,post));

    }

    //좋아요 삭제하는 메서드

    public void deleteLikeMapping(User user,Post post) {


        Optional<Favorite> favorite = favoriteRepository.findByUserIdAndPostId(user,post);

        favorite.ifPresent(favoriteRepository::delete);




    }


}
