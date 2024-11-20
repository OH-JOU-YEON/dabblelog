package com.dabblelog.side.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(
                        AbstractHttpConfigurer::disable
                )
                .headers(
                        (headerConfig) -> headerConfig.frameOptions(
                                HeadersConfigurer.FrameOptionsConfig::disable
                        )
                )
                .authorizeHttpRequests((authorizeRequest) -> authorizeRequest

                        //홈이랑 로그인 페이지만 아무나 접근 가능 나머지는 인증된 사용자만 접근 가능
                        //페이지 다 만들고 코드 추가

                        .anyRequest().permitAll()
                )
                .logout( // 로그아웃 성공 시 / 주소로 이동
                        (logoutConfig) -> logoutConfig.logoutSuccessUrl("/")
                )
                // OAuth2 로그인 기능에 대한 여러 설정
                .oauth2Login(Customizer.withDefaults()); // 아래 코드와 동일한 결과
        /*
                .oauth2Login(
                        (oauth) ->
                            oauth.userInfoEndpoint(
                                    (endpoint) -> endpoint.userService(customOAuth2UserService)
                            )
                );
        */

        return http.build();
    }
}
