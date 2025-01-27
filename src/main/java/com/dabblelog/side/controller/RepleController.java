package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.dto.ReRepleDTO;
import com.dabblelog.side.domain.dto.ReplyDTO;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.RepleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class RepleController {

    @Autowired
    RepleService repleService;

    @Autowired
    BlogService blogService;


    @PostMapping("/reple/create")
    public void createReple() {
        //만들고 답글 dto 던져서
    }

    @ResponseBody
    @PostMapping("/reple/reply")
    public ReRepleDTO createReReple(HttpServletRequest request, @RequestBody ReplyDTO replyDTO) {

        HttpSession httpSession = request.getSession(false);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        Reple reple = repleService.createReple(replyDTO);

        String authorBlog = "/dabblelog/" + blogService.getBlogName(sessionUser.getEmail());


        return new ReRepleDTO(reple,authorBlog);

    }
}
