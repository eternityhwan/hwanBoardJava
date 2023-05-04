package com.hwan.qnaboard.config;


import com.hwan.qnaboard.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // 이 클래스가 설정 클래스라는 것을 알림
@EnableWebSecurity // 스프링 시큐리티를 활성화 한다. 이 어노테이션을 서야 HttpSecurity 클래스를 사용할 수 있다.
@Slf4j
//@EnableGlobalMethodSecurity // deprecated 되었다. 스프링 3.0에서 EnableMethodSecurity 로 대체
@EnableMethodSecurity(proxyTargetClass = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

     // 생성자 주입으로
    private final UserService userService;

    private static final String[] PERMIT_URL_ARRAY = {
        /* swagger v2 */
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        /* swagger v3 */
        "/v3/api-docs/**",
        "/swagger-ui/**"
    };

    @Bean // 해당 메서드가 bean으로 등록됨을 의미,
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeHttpRequests() // 인가 요청이 오면 해야할 일 밑에 정의
            .requestMatchers("/auth/**")
            .permitAll() // login 없이 접근 허용하는 url
                .requestMatchers("/boards/readall") // 이 요청이 들어오면
                .permitAll() // 접근을 허용한다
                .requestMatchers(PERMIT_URL_ARRAY) // 이 요청들은
                .permitAll() // 접근을 허용한다
//              .hasRole("ADMIN")
                .anyRequest() // 어떤 요청이라도
                .authenticated() // 인증이 되어야 들어갈 수 있다
                .and()
            // 폼로그인 설정
            .formLogin()
                .loginPage("/auth/form") // 커스텀 로그인 페이지 지정 (스프링시큐리티 기본 로그인화면은 이 설정으로 없어진다.)
//                .loginProcessingUrl("/auth/login") // (유저아이디와 패스워드 받을 url 지정)
//                .usernameParameter("userName") // submit 할 아이디
//                .passwordParameter("password") // submit 할 비밀번호
                .defaultSuccessUrl("/boards/readall", true)
//                .failureUrl("/auth/loginform?=error=true") // 로그인 실패했을 때 이동할 url 지정.permitAll()
                .permitAll()
                .and()
//            .oauth2ResourceServer()
//            .jwt()
//            .jwkSetUri()
//            .jwtAuthenticationConverter()
            .logout(Customizer.withDefaults()); // 기본 로그아웃으로 인증해제

//                .logoutUrl("/auth/logout")
//                .logoutSuccessUrl("/hi")
//            .invalidateHttpSession(true)
//                .clearAuthentication(true)
//            .deleteCookies("JSESSIONID")
//                .permitAll();
        //            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //            .and()

    return http.build();
    }
}
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
/**
 * 이 코드는 Spring Security를 설정하는 Java Config 클래스입니다. 각 부분을 하나씩 살펴보겠습니다.
 *
 * SecurityFilterChain filterChain(HttpSecurity http) 메서드: HttpSecurity 객체를 매개변수로 받아서 SecurityFilterChain 객체를 반환합니다.
 * 이 메서드에서 Spring Security의 설정을 정의합니다.
 * http.csrf().disable() 메서드: CSRF 공격을 막기 위해 CSRF 토큰을 생성하는 기능을 비활성화합니다.
 * http.headers().frameOptions().disable() 메서드: X-Frame-Options 헤더를 설정하여 Clickjacking 공격을 막습니다.
 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 메서드: 세션 관리를 설정합니다. 여기서는 세션을 사용하지 않도록 설정합니다.
 * .formLogin().disable() 메서드: 로그인 폼 기능을 비활성화합니다.
 * .httpBasic().disable() 메서드: HTTP 기본 인증 기능을 비활성화합니다.
 * .apply(new CustomDsl()) 메서드: CustomDsl 클래스에서 정의한 필터를 적용합니다.
 * .authorizeRequests() 메서드: 요청에 대한 인가 처리를 설정합니다.
 * .antMatchers("/api/v1/user/**") 메서드: "/api/v1/user/**" 패턴에 해당하는 URL 요청에 대해 설정합니다.
 * .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") 메서드:
 * ROLE_USER, ROLE_MANAGER, ROLE_ADMIN 권한이 있는 사용자만 해당 요청에 접근할 수 있습니다.
 *
 */


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


