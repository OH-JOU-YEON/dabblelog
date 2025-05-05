package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Reple;
import com.dabblelog.side.domain.User;
import com.dabblelog.side.domain.dto.SettingProfileDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SettingService {

    private final BlogService blogService;

    private final UserRepository userRepository;



    public SettingProfileDTO getSettingProfile(String email) {

        User user = userRepository.findByEmail(email).get();

        String blogName = blogService.getBlogName(email);

        return new SettingProfileDTO(user,blogName);
    }
}
