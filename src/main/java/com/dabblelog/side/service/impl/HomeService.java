package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Post;
import com.dabblelog.side.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeService {


    PostRepository postRepository;

    //좋아요가 많이 눌린 게시물 찾아주는 메서드


    //팔로잉하는 사람들의 게시물 찾아주는 메서드

    //그냥 최신 게시물 찾아주는 메서드


}
