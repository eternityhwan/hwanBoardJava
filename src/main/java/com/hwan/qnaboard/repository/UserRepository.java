package com.hwan.qnaboard.repository;

import com.hwan.qnaboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
}
