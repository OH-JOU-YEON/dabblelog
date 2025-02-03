package com.dabblelog.side.config.auth;

import com.dabblelog.side.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }



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


                        .requestMatchers("/posts/new").hasRole(Role.USER.name())
                        .requestMatchers("/", "/login", "/login/**", "/logout/*",
                                "/css/**","/js/**","/fonts/**","/img/**","/mail/**" ).permitAll()
                        .anyRequest().authenticated()
                )
                .logout(
                        (logoutConfig) -> logoutConfig.logoutSuccessUrl("/")
                )

                .oauth2Login(Customizer.withDefaults());


        return http.build();
    }
}
