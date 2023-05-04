package com.hwan.qnaboard.controller;

import com.hwan.qnaboard.entity.User;
import com.hwan.qnaboard.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor //  final 필드나 @NonNull 어노테이션이 붙은 필드들에 대한 생성자를 자동으로 생성해주는 기능을 제공
@RequestMapping("/auth") public class UserController {
    @Autowired private UserService userService;

    //     로그인 기능 -- 폼으로 이동
    @GetMapping("/form") public String moveLoginForm() {
        log.info("폼으로 이동 기능 작동");
        return "articles/login";
    }

    // 로그인 기능
    @PostMapping("/login") public String login(
        @RequestParam String username,
        @RequestParam String password,
        HttpSession session, Model model,
        RedirectAttributes redirectAttributes) {
        User foundUser = userService.findByUserName(username);
        log.info("로그인 기능 메서드 작동");
        if (foundUser != null && foundUser.getPassword().equals(password)) {
            log.info("로그인 성공 알람");
            session.setAttribute("user", foundUser);
            redirectAttributes.addFlashAttribute("successMessage", "로그인에 성공했습니다.");
            return "redirect:/boards/readall";
            //            return "forward:/boards/readall";
        } else {
            log.info("로그인 실패 알람");
            model.addAttribute("errorMessage", "로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요.");
            return "articles/login";
        }
    }

    // 회원가입 기능 -- 폼으로 이동
    @GetMapping("/join") public String moveSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "articles/join";
    }

    // 회원가입 기능
    @PostMapping("/signup") public String signUp(@ModelAttribute("user") User user, Model model) {
        User existingUser = userService.findByUserName(user.getUserName());
        if (existingUser != null) {
            model.addAttribute("errorMessage", "동일한 아이디가 이미 존재한다.");
            return "articles/join";
        }
        userService.saveUser(user);
        model.addAttribute("successMessage2", "회원가입이 성공적으로 완료되었습니다.");
        return "redirect:/boards/readall";
//        return "redirect:/login";
    }
}
