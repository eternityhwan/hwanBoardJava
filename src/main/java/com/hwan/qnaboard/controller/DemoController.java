package com.hwan.qnaboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    // 템플릿 엔진으로 데이터를 넘겨 rok 을 출력하는 방법

    @GetMapping(value = "/hi")
    public String thymeleafCon(Model model) {
        model.addAttribute("username","rok");
        return "articles/test";
    }
}
