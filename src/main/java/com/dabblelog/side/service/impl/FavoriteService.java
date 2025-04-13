package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.repository.FavoriteRepository;
import com.dabblelog.side.repository.RepleRepository;
import com.dabblelog.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {


   private final UserRepository userRepository;

   private final FavoriteRepository favoriteRepository;


    RepleRepository repleRepository;

    public Page<PostHomeDTO> getLikedPages(String email, Pageable pageable) {

        User user = userRepository.findByEmail(email).get();

        return favoriteRepository.findAllByUserId(user,pageable).map(s -> new PostHomeDTO(s.getPostId(),repleRepository.countByPostId(s.getPostId())));

    }


}
