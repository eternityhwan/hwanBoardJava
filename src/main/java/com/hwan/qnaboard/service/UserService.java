package com.hwan.qnaboard.service;

import com.hwan.qnaboard.entity.User;
import com.hwan.qnaboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    // 로그인 과정 로직
    // 1. Login ID/PW를 기반으로 인증 객체를 생성
    // 2. 인증메서드를 통해 요청된 유저의 검증 진행
    // 3. 검증이 완료되었다면 인증 객체를 기반으로 JWT 토큰을 생성한다.

}


// 인증기능
//    public boolean authenticate(String userName, String password) {
//        User user = userRepository.findByUserName(userName);
//        if (user != null && user.getPassword().equals(password)) {
//            return true;
//        }
//        return false;
//    }
