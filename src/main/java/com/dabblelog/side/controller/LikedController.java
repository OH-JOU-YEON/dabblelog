package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.dto.LikedDTO;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.FavoriteService;
import com.dabblelog.side.service.impl.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class LikedController {


   private final FavoriteService favoriteService;


   private final BlogService blogService;


   private final PostService postService;


   private final PostRepository postRepository;

    //내가 좋아요 누른 게시물들을 볼 수 있는 페이지

    @GetMapping("/liked")
    public String mappingHome(Model model, HttpServletRequest request, @PageableDefault(page=0, size=9)Pageable pageable) {

        HttpSession session = request.getSession(false);

        if(session == null) {

            return "redirect:/";
        }

        SessionUser sessionUser = (SessionUser) session.getAttribute("user");

        String email = sessionUser.getEmail();

        Page<PostHomeDTO> postHomeDTOS = favoriteService.getLikedPages(email,pageable);

        String blogName = blogService.getBlogName(email);

        model.addAttribute("loginOrNot","새 글 작성하기");
        model.addAttribute("path","/write");
        model.addAttribute("email",email);
        model.addAttribute("list",postHomeDTOS);
        model.addAttribute("myBlogURL","/dabblelog/" + blogName);



        return "basic/Liked";
    }

    @ResponseBody
    @PostMapping("/liked/modiCount")
    public void modifyLikeCount(@RequestBody LikedDTO likedDTO) {

        //포스트 얻어와서, 저 dto만큼 좋아요 총계 수정하고 재저장하기

        Post post = postService.getPostIdByURL(likedDTO.getUrl(), likedDTO.getUuid());

        postRepository.save(post.modifyLikeCount(likedDTO.getLikeCount()));

    }
}
