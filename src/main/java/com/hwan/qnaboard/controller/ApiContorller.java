package com.hwan.qnaboard.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@ComponentScan
@Tag(name = "test", description = "Hello world 반환하는 api입니다.") // api 그룹설정 어노테이션
// @ApiIgnore api 나오지 않게 하기
@RestController // RESTful Web Service를 제공하는 역할을 한다는 것을 나타냄.
@Slf4j
public class ApiContorller {

    // Json 형태로 Data를 출력한다.
    // localhost:portNumber/api 로 요청을 받으면
    // Rok Hello World 가 반환되는 메서드입니다.

    @Operation(summary = "Hello World 반환", description = "헬로 월드 반환") // api 상세 정보 설정을 위한 어노테이션션    @GetMapping("/api")
    public String apiController() {
        return "Rok Hello World";
    }

}
