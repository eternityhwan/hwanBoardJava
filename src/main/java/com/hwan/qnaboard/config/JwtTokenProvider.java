package com.hwan.qnaboard.config;


import com.hwan.qnaboard.constant.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



@Component
@Slf4j
public class JwtTokenProvider {
    // JWT 토큰 생성, 토큰 복호화 및 정보 추출, 토큰 유효성 검증 기능이 구현된 클래스.

    @Value("${jwt.secret}") // application.yml에 있는 변수값을 읽어오기위해사용한다
    private static String secret; // application.yml에 있던 jwt.secret 값을 secret 변수에 저장한다.


    // Jwt 토큰 생성
    public String createJwt(String username, Role role) {
        Claims claims = Jwts.claims().setSubject(username); // JWT payload에 저장되는 정보
        claims.put("roles", role.toString()); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();

        // builder 패턴을 사용해서 JWT 생성성
       return Jwts.builder()
            // Header 정의
            .setHeaderParam("type", "JWT")
            .setHeaderParam("alg", "HS256")
            .setIssuedAt(now) // 토큰 발행 시간 정보
            // Payload 정의
            .setSubject(username)
            .claim("role", role)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 7200000)) // 토큰 만료시간 정의 2시간
            // Signature 정의
            .signWith(SignatureAlgorithm.HS256, (secret.getBytes(StandardCharsets.UTF_8))) // 사용할 암호화 알고리즘과 signature에 들어갈 secret값 세팅
            .compact(); // 위 설정대로 JWT 토큰을 생성한다는 의미.
    }



    // jwt 토큰에서 인증 정보 조회
    public Authentication readAuthentication(String jwttoken) {
        Claims claims = Jwts.parser()
            .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
            .parseClaimsJws(jwttoken)
            .getBody();

        List<SimpleGrantedAuthority> authorities = ((List<String>) claims.get("roles")).stream()
            .map(role -> new SimpleGrantedAuthority(role))
            .collect(Collectors.toList());

        String username = claims.getSubject();

        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null;
    }

    /**
     * 회원 정보를 추출하기 위해서는 JwtTokenProvider에서 사용한 UserDetailsService 구현체에서
     * loadUserByUsername 메서드를 구현하여야 합니다.
     * 이 메서드에서는 username을 이용하여 해당 회원 정보를 조회하고, UserDetails 객체를 리턴해주어야 합니다.
     */

}




//   검증증 과정//    public static Claims parseJwt(String jwt) throws JwtException {
//        return
//            Jwts.parserBuilder()
//            .setSigningKey(new SecretKeySpec(
//                secret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName()))
//            .build()
//            .parseClaimsJws(jwt)
//            .getBody();
//    }

    // JWT에서 특정 클레임 값을 추출하는 메서드
//    public static String extractClaim(String jwt, String claimName) throws JwtException {
//        Claims claims = parseJwt(jwt);
//        return claims.get(claimName, String.class);
//    }
//}
