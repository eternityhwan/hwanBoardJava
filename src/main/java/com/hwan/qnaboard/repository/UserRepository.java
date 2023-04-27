package com.hwan.qnaboard.repository;

import ch.qos.logback.core.model.INamedModel;
import com.hwan.qnaboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
}
