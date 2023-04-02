package com.hwan.qnaboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name ="board")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime modifiedDate;


    @PrePersist
    // JPA 엔티티(Entity)가 비영속(new/transient) 상태에서 영속(managed) 상태가 되는 시점 이전에 실행됩니다
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        modifiedDate = now;
    }

    @PreUpdate
    // 영속 상태의 엔티티를 이용하여 데이터 업데이트를 수행하기 이전에 실행된다
    public void preUpdate() {
        modifiedDate = LocalDateTime.now();
        if (createdDate == null) {
            createdDate = modifiedDate;
        }
    }
}
