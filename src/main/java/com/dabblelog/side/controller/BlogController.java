package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.BlogPostDTO;
import com.dabblelog.side.domain.dto.BlogProfileDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BlogController {


    private final UserRepository userRepository;


   private final BlogRepository blogRepository;


   private final BlogService blogService;

    @GetMapping("/dabblelog/{blogName}")
    public String blogMapping(Model model, HttpServletRequest
            request, @PathVariable String blogName, @PageableDefault(page=0, size=6, sort="createdDay", direction= Sort.Direction.DESC) Pageable pageable) {

        HttpSession session = request.getSession(false);


        if(session == null) {

            model.addAttribute("email","dabblelog.com");
            model.addAttribute("myBlogURL","/oauth2/authorization/google");

        } else {
            SessionUser sessionUser = (SessionUser) session.getAttribute("user");

            model.addAttribute("email",sessionUser.getEmail());
            model.addAttribute("myBlogURL","/dabblelog/" + blogService.getBlogName(sessionUser.getEmail()));

        }

        Page<BlogPostDTO> getBlogPostDTOS = blogService.getBlogPostDTOS(blogName,pageable);
        BlogProfileDTO getBlogProfileDTO = blogService.getBlogProfileDTO(blogName);

        model.addAttribute("list",getBlogPostDTOS);

        model.addAttribute("profile",getBlogProfileDTO);

        int nowPage = getBlogPostDTOS.getPageable().getPageNumber() + 1;
        int startPage =  Math.max(nowPage - 2, 1);
        int endPage = Math.min(nowPage+5, getBlogPostDTOS.getTotalPages());
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("thisBlog","/dabblelog/" + blogName);

        return "basic/Posts";
    }





    Blog updateBlog(String email) {
        User user = userRepository.findByEmail(email).get();


        Optional<Blog> blogWrapper = blogRepository.findById(user.getId());

        return blogWrapper.orElseGet(() -> blogRepository.save(new Blog(user)));
    }



}
