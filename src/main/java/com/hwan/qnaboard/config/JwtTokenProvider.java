package com.hwan.qnaboard.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;


// JWT 토큰 생성, 토큰 복호화 및 정보 추출, 토큰 유효성 검증 기능이 구현된 클래스.
@Component
@Slf4j
public class JwtTokenProvider {

    /** JWT는 총 세 부분
     * Header -- 토큰 타입과 암호화 알고리즘 정의
     * Payload -- 클라이언트와 서버 간에 교환되는 정보를 key-value로 담는다.
     * Signature -- Header와 Payload를 암호화하여 생성한 서명 값.
    */

    @Value("${jwt.secret}") // application.yml에 있는 변수값을 읽어오기위해사용한다
    private static String secret; // application.yml에 있던 jwt.secret 값을 secret 변수에 저장한다.

    public String createJwt(String username, String role) {

            Date now = new Date(); // 현재 시각 정의
            return Jwts.builder()
                // Header 정의
                .setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")
                // Payload 정의
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7200000)) // 2시간
                // Signature 정의
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
        }
    // jwt 토큰 검증
    public static Claims parseJwt(String jwt) throws JwtException {
        return
            Jwts.parserBuilder()
            .setSigningKey(new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName()))
            .build()
            .parseClaimsJws(jwt)
            .getBody();
    }

    // JWT에서 특정 클레임 값을 추출하는 메서드
    public static String extractClaim(String jwt, String claimName) throws JwtException {
        Claims claims = parseJwt(jwt);
        return claims.get(claimName, String.class);
    }

}
