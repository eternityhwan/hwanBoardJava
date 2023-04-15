package com.hwan.qnaboard.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RESTful Web Service를 제공하는 역할을 한다는 것을 나타냄.
public class ApiContorller {

    // Json 형태로 Data를 출력한다.
    // localhost:portNumber/api 로 요청을 받으면
    // Rok Hello World 가 반환되는 메서드입니다.

    @GetMapping("/api")
    public String apiController() {
        return "Rok Hello World";
    }

}
