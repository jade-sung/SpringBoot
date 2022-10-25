package com.example.hello.config;

import com.example.hello.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //환경설정
@EnableWebSecurity //모든 URL 요청에 스프링 시큐리티의 제어를 받게함
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize("isAuthenticated()") 를 사용하기 위함
public class SecurityConfig {
    private final UserSecurityService userSecurityService;
    @Autowired
    public SecurityConfig(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @Bean //시큐리티필터체인 빈을 생성해서 세부 설정 가능
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll() // 인증되지않은 모든 요청을 허락함
                .and().formLogin().loginPage("/hello/user/login").defaultSuccessUrl("/") // 로그인페이지 URL 및 로그인 성공시 이동할 페이지
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/hello/user/logout")).logoutSuccessUrl("/").invalidateHttpSession(true);
                // 로그아웃 URL과 로그아웃 성공시 URL 설정. + 로그아웃 시 세션 삭제.
        return http.build();
    }

    @Bean //BCryptPasswordEncoder를 new로 생성하기보다 PasswordEncoder를 빈으로 등록
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //스프링 시큐리티의 인증을 담당. 해당 빈 생성시 위의 PasswordEncoder와 UserSecurityService가 자동으로 설정됨.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
