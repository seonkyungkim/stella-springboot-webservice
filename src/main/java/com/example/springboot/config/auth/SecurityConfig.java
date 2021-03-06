package com.example.springboot.config.auth;

import com.example.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  //Enables Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disabled.

                .and()
                    .authorizeRequests()    // URL별 권한 관리를 설정하는 옵션의 시작점. authorizedRequests가 선언되어야 andMatchers 사용가능
                    // antMatchers : 권한관리 대상 지정하는 옵션. URL, HTTP 메소드별로 관리가능.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  // 전체열람권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // user권한을 가진 사람만 접근가능.
                    .anyRequest().authenticated()   // 나머지 URL들은 인증된 사용자들 즉, 로그인한 사용자에게만 허용

                .and()
                    .logout()
                        .logoutSuccessUrl("/")  // 로그아웃 기능에 대한 여러 설정의 진입점. 로그아웃 성공 시 / 주소로 이동

                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // OAuth2로그인 성공 이후 사용자 정보를 가져올 때의 설정
                            // 소셜로그인 성공시 후속조치를 진행할 UserService 인터페이스의 구현체를 등록.
                            // 리소스서버(소셜서비스 등)에서 사용자정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시가능
                            .userService(customOAuth2UserService);
    }
}
