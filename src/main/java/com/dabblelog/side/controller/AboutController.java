package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.About;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.dto.AboutDTO;
import com.dabblelog.side.service.impl.AboutService;
import com.dabblelog.side.service.impl.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class AboutController {

    @Autowired
    BlogService blogService;

    @Autowired
    AboutService aboutService;

    @PostMapping("/about/create")
    public void getAbout(HttpServletRequest request, Model model, @RequestBody AboutDTO aboutDTO) {

        HttpSession session = request.getSession(false);

        SessionUser sessionUser = (SessionUser) session.getAttribute("user");

        Blog blog = blogService.ifBlogIsNotExistCreateBlog(sessionUser.getEmail());

        About about = aboutService.getAboutByBlog(blog,aboutDTO);


    }
}
