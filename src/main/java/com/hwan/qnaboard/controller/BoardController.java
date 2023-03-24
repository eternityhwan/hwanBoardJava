package com.hwan.qnaboard.controller;

import com.hwan.qnaboard.domain.Board;
import com.hwan.qnaboard.repository.BoardRepository;
import com.hwan.qnaboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("value = /qnaboards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 컨트롤러에서 Thymeleaf 템플릿 파일의 이름과 변수 값을 설정하여 뷰를 반환해야 합니다

    // Create function
    @GetMapping ("/create")
    public String moveCreateForm() {
        return "articles/create;";
    }

    @PostMapping("/create")
    public String create(Board board) {
        boardService.create(board);
        return "redirect:articles/board/";
    }

    // Read function
    // 1. Read ALL
    @GetMapping("/")
    public String getAllArticles(Model model) {
        List<Board> boardList = boardService.findAll();
        model.addAttribute("board", boardList);
        return "articles/board";
    }

    // 2. Read each
    // Update function
    @GetMapping("/update/{id}")
    public String moveUpdateForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "articles/update";
    }

    @PostMapping("/update")
    public String update(Board board) {
        boardService.update(board);
        return "redirect:articles/board/";
    }

    // Delete function
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:articles/board/";
    }

}
