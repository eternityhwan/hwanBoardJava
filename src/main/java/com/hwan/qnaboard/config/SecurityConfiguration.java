package com.hwan.qnaboard.config;


import com.hwan.qnaboard.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.naming.AuthenticationException;

@Configuration // 이 클래스가 설정 클래스라는 것을 알림
@EnableWebSecurity // 스프링 시큐리티를 활성화 한다. 이 어노테이션을 서야 HttpSecurity 클래스를 사용할 수 있다.
@Slf4j
//@EnableGlobalMethodSecurity // deprecated 되었다. 스프링 3.0에서 EnableMethodSecurity 로 대체
@EnableMethodSecurity(proxyTargetClass = true)
public class SecurityConfiguration {



    @Autowired
    private UserService userService;

    @Bean // 해당 메서드가 bean으로 등록됨을 의미,
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .disable()
            .csrf()
            .disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
            .authorizeHttpRequests()
//            .requestMatchers("/login")
//            .permitAll()
//            .requestMatchers("/login")
//            .hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
//            .loginPage("/auth/login") // 로그인페이지 url 적어주기
            .defaultSuccessUrl("/boards/readall", true)
            .failureUrl("/hi") // 로그인 실패했을 때 이동할 url 지정
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/hi");

    return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
}

        /** http.build()는 HttpSecurity를 사용하여 구성한 필터 체인을 빌드하여
         * SecurityFilterChain으로 반환하는 메서드입니다.
         * WevSecurityConfigurerAdapter를 상속하는 방식은 Deprecated 되었기 때문에 더 이상 사용할 수 없습니다.
         * 따라서 SecurityFilterChain을 반환해주어야 합니다 */
        /**
         * csrf().disable() CSRF(Cross-Site Request Forgery) 공격 방어 비활성화
         * 쿠키를 사용하지 않으면 csrf는 자동 방어
         * sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) JWT를 사용하기 때문에 세션을 사용하지 않는다는 설정.
         * authorizeHttpRequests() 인증 규칙을 정의하기 위해 사용하는 메서드(인증된 http 요청에 대해서만 접근을 허용하는 설정 진행)
         * anyRequest().authenticated(); 모든 요청에 대해 인증된 사용자만 접근 가능하도록 설정(모든 요청에 대해 인증이 필요하다는 설정)
         * .requestMatchers().authenticated(); // antMatcher() Deprecated requestMatchers를 사용해줘야한다.
         * hasRole 메서드도 requestMatchers 와 함께 쓰인다.
         * formLogin() : 폼 로그인 기능을 활성화 합니다.
         * defaultSuccessUrl("/boards/readall", true): 로그인 후 이동할 페이지를 설정합니다. 이 경우 "/boards/readall" 페이지로 이동합니다.
         * permitAll(): 로그인 페이지에 대해서는 인증을 거치지 않고 접근 가능하도록 설정합니다.
         * logout(): 로그아웃 기능을 활성화합니다.
         * requestMatchers와 ansyRequest() 둘 중 하나를 사용해야합니다.
         * */

        /**
         * passwordEncoder Jwt를 사용하기 위해서는 기본적으로 password encoder가 필요한데 여기서는 Bycrypt encoder를 사용.
         *
         * */


