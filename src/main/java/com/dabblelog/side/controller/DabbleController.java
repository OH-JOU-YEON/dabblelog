package com.dabblelog.side.controller;


import com.dabblelog.side.config.auth.dto.SessionUser;
import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Dabble;
import com.dabblelog.side.domain.dto.DabbleDTO;
import com.dabblelog.side.domain.dto.DabbleDaysDTO;
import com.dabblelog.side.domain.dto.DabblePostDTO;
import com.dabblelog.side.domain.dto.MonthDTO;
import com.dabblelog.side.service.impl.BlogService;
import com.dabblelog.side.service.impl.DabbleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DabbleController {

    @Autowired
    DabbleService dabbleService;

    @Autowired
    BlogService blogService;

    @ResponseBody
    @PostMapping("/dabble/monthRight")
    public DabbleDTO getMonthAfter(HttpServletRequest request, @RequestBody MonthDTO monthDTO) {

        HttpSession session = request.getSession(false);

        SessionUser sessionuser = (SessionUser) session.getAttribute("user");

        Blog blog = blogService.getBlogByEmail(sessionuser.getEmail());

        String yearAndMonth = monthDTO.getYear() + monthDTO.getMonth();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

        // 문자열 -> Date
        LocalDateTime afterMonth = LocalDateTime.parse(yearAndMonth, formatter).plusMonths(1);

        Dabble dabble = dabbleService.getDabbleByDate(afterMonth);

        List<DabblePostDTO> dabblePostDTOS = dabbleService.getDabblePostDTOs(afterMonth,blog);

        List<DabbleDaysDTO> dabbleDaysDTOS = dabbleService.getDivDays(afterMonth,dabblePostDTOS);

        return new DabbleDTO(dabble,dabbleDaysDTOS);





    }

    @ResponseBody
    @PostMapping("/dabble/monthLeft")
    public DabbleDTO getMonthBefore(HttpServletRequest request, @RequestBody MonthDTO monthDTO) {

        HttpSession session = request.getSession(false);

        SessionUser sessionuser = (SessionUser) session.getAttribute("user");

        Blog blog = blogService.getBlogByEmail(sessionuser.getEmail());

        String yearAndMonth = monthDTO.getYear() + monthDTO.getMonth();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");

        // 문자열 -> Date
        LocalDateTime afterMonth = LocalDateTime.parse(yearAndMonth, formatter).minusMonths(1);

        Dabble dabble = dabbleService.getDabbleByDate(afterMonth);

        List<DabblePostDTO> dabblePostDTOS = dabbleService.getDabblePostDTOs(afterMonth,blog);

        List<DabbleDaysDTO> dabbleDaysDTOS = dabbleService.getDivDays(afterMonth,dabblePostDTOS);

        return new DabbleDTO(dabble,dabbleDaysDTOS);





    }

    @GetMapping("/dabble")
    public String getDabbles(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null ) {
            SessionUser sessionuser = (SessionUser) session.getAttribute("user");
            model.addAttribute("loginOrNot","새 글 작성하기");
            model.addAttribute("path","/write");
            model.addAttribute("email",sessionuser.getEmail());

            Blog blog = blogService.ifBlogIsNotExistCreateBlog(sessionuser.getEmail());

            LocalDateTime localDateTime = LocalDateTime.now();

            Dabble dabble = dabbleService.getDabbleByDate(localDateTime);

            List<DabblePostDTO> dabblePostDTOS = dabbleService.getDabblePostDTOs(localDateTime,blog);

            List<DabbleDaysDTO> dabbleDaysDTOS = dabbleService.getDivDays(localDateTime,dabblePostDTOS);

            DabbleDTO dabbleDTO = new DabbleDTO(dabble,dabbleDaysDTOS);

            model.addAttribute("dabble",dabbleDTO);



        } else {
            return "redirect:/";
        }

        return "/basic/Dabble";
    }
}
