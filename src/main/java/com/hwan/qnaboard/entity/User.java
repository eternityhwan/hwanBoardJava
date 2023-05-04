package com.hwan.qnaboard.entity;

import com.hwan.qnaboard.constant.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Getter
@NoArgsConstructor
public class User implements Serializable {
    // 클라이언트에게 토큰을 보내기위한 DTO(Entity) 생성
    // Serializable 자바에서 직렬화가 가능한 클래스를 만들기위한 인터페이스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private Role role;
}
