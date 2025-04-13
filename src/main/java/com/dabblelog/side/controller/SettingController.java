package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.ModiTitleDTO;
import com.dabblelog.side.domain.dto.ModifyProfileTextDTO;
import com.dabblelog.side.domain.dto.ProfileImgDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import com.dabblelog.side.service.impl.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class SettingController {


    UserRepository userRepository;


    BlogService blogService;


    BlogRepository blogRepository;

    //설정 호출 메서드
    @GetMapping("/setting")
    public String mappingSetting(Model model, HttpServletRequest request) {

        HttpSession httpSession = request.getSession(false);

        if(httpSession == null) {

            return "redirect:/";
        }


        return "/basic/Setting";
    }

    @PostMapping("/setting/profileText")
    public void modifyProfileText(HttpServletRequest request, @RequestBody ModifyProfileTextDTO modifyProfileTextDTO) {
        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        User user = userRepository.findByEmail(sessionUser.getEmail()).get();

        userRepository.save(user.updateReadme(modifyProfileTextDTO.getNickname(), modifyProfileTextDTO.getReadme()));


    }

    @PostMapping("/setting/deleteUser")
    public void deleteUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        User user = userRepository.findByEmail(sessionUser.getEmail()).get();

        userRepository.delete(user);

    }

    @PostMapping("/setting/modiTitle")
    public void modiTitle(HttpServletRequest request, @RequestBody ModiTitleDTO modiTitleDTO) {

        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        Blog blog = blogService.getBlogByEmail(sessionUser.getEmail());

        blogRepository.save(blog.updateBlogName(modiTitleDTO.getBlogName()));

    }

    @PostMapping("/setting/profileImg")
    public void modiProfileImg(HttpServletRequest request, @RequestBody ProfileImgDTO profileImgDTO) {
        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        User user = userRepository.findByEmail(sessionUser.getEmail()).get();

        userRepository.save(user.updatePicture(profileImgDTO.getProfileImg()));

    }



}
