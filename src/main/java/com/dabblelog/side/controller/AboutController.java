package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.About;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.dto.AboutDTO;
import com.dabblelog.side.domain.dto.BlogProfileDTO;
import com.dabblelog.side.service.impl.AboutService;
import com.dabblelog.side.service.impl.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public void createAbout(HttpServletRequest request, @RequestBody AboutDTO aboutDTO) {

        HttpSession session = request.getSession(false);

        SessionUser sessionUser = (SessionUser) session.getAttribute("user");

        Blog blog = blogService.ifBlogIsNotExistCreateBlog(sessionUser.getEmail());

        About about = aboutService.getAboutByBlog(blog,aboutDTO);


    }

    @GetMapping("/dabblelog/{blogName}/about")
    public String getAbout(Model model, HttpServletRequest request, @PathVariable String blogName) {
        HttpSession session = request.getSession(false);


        if(session == null) {

            model.addAttribute("email","dabblelog.com");
            model.addAttribute("myBlogURL","/oauth2/authorization/google");

            model.addAttribute("path", "/oauth2/authorization/google");
            model.addAttribute("loginOrNot", "구글 로그인");

        } else {
            SessionUser sessionUser = (SessionUser) session.getAttribute("user");

            model.addAttribute("email",sessionUser.getEmail());
            model.addAttribute("myBlogURL","/dabblelog/" + blogService.getBlogName(sessionUser.getEmail()));

            model.addAttribute("path", "/write");
            model.addAttribute("loginOrNot", "새 글 작성하기");

            //세션 블로그하고 url하고 일치하는지 알아본 후에 맞으면 수정하기 버튼 속성 block 아니면 none

            if(blogService.getBlogByName(blogName).getUser().getEmail().equals(sessionUser.getEmail())) {

                model.addAttribute("ifMyAbout","block");
            } else {
                model.addAttribute("ifMyAbout","none");
            }

        }

        BlogProfileDTO blogProfileDTO = blogService.getBlogProfileDTO(blogName);

        model.addAttribute("thisBlogURL","/dabblelog/" + blogName);


        model.addAttribute("profile",blogProfileDTO);

        if(aboutService.isAboutExist(blogName)) {

            About about = aboutService.getAboutByBlogName(blogName);
            AboutDTO aboutDTO = new AboutDTO(about);

            model.addAttribute("about",aboutDTO);

        } else {

            AboutDTO aboutDTO = new AboutDTO("소개말이 없습니다");

            model.addAttribute("about",aboutDTO);

        }

        return "basic/About";
    }
}
