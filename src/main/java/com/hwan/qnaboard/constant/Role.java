package com.hwan.qnaboard.constant;

public enum Role {
    ADMIN("ROLE_ADMIN"), // ROLE_ADMIN 스프링 시큐리티에서 기본적으로 사용하는 관리자 권한.
    USER("ROLE_USER"),
    WRITER("ROLE_WRITER");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}

/**
1. 로그인을 한 애만 글을 작성 할 수 있게 한다.
    2. 수정, 삭제는 어드민과 작성자만 가능하게 한다.

    글 생성 --> ADMIN, USER
    글 삭제 --> ADMIN, WRITER
    글 수정 --> ADMIN, WRITER
    글 열람 --> ADMIN, USER, WRITER
*/

    // 이런식으로 롤 사용 가능
//    String token = Jwts.builder()
//        .setSubject(username)
//                .claim("role", Role.ADMIN.toString())
//        .setExpiration(expiryDate)
//                .signWith(secretKey)
//                .compact();


