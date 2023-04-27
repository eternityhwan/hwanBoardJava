package com.hwan.qnaboard.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

//@Component // 해당 클래스가 bean으로 관리되도록 설정
//public class JwtTokenUtil implements Serializable {
//    // Serializable 를 구현한 클래스이다
//    // JWT 토큰을 생성하고 검증하는 기능을 담당한다.
//
//    // 토큰의 유효시간을 나타내는 상수, 7시간으로 설정되어있다.
//    public static final long JWT_TOKEN_VALIDITY = 7 * 60 * 60;
//    @Value("${jwt.secret}") // application.yml에 있는 변수값을 읽어오기위해사용한다
//    private String secret; // application.yml에 있던 jwt.secret 값을 secret 변수에 저장한다.
//
//    // 토큰에서 유저네임을 가져오는 메소드
//    public String getUsernameFromToken(String token) {
//        // 먼저 Jwts.parser()를 호출하여 JWT 토큰 파서 객체를 생성한 다음,
//        // setSigningKey(secret) 메소드를 사용하여 파서 객체에 secret 값을 설정
//        // parseClaimsJws(token)을 호출하여 JWT 토큰을 파싱하고,
//        // getBody().getSubject()를 호출하여
//        // 토큰에서 추출한 subject 값을 반환
//        return Jwts.parser()
//            .setSigningKey(secret).parseClaimsJws(token)
//            .getBody()
//            .getSubject();
//    }
//
//    // 토큰의 만료시간을 가져오는 메소드
//    public Date getExpirationDateFromToken(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
//    }
//
//    // 토큰이 만료되었는지 확인하는 메소드
//    public Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    // 토큰 생성 메소드
//    public String generateToken(UserDetails userDetails) {
//        return Jwts.builder()
//            .setSubject(userDetails.getUsername())
//            .setIssuedAt(new Date(System.currentTimeMillis()))
//            .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 토큰 유효시간 1시간
//            .signWith(SignatureAlgorithm.HS512, secret)
//            .compact();
//    }
//}
