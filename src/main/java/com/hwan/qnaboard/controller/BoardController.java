package com.hwan.qnaboard.controller;

import com.hwan.qnaboard.model.Board;
import com.hwan.qnaboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // Create function
    @GetMapping("/create")
    public String moveCreateForm() {
        return "articles/create";
    }

    @PostMapping("/create")
    public String create(Board board) {
        boardService.create(board);
        return "redirect:/boards/readall";
    }

    // Read function
    // 1. Read ALL
    @GetMapping("/readall")
    public String getAllArticles(Model model) {
        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
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
        return "redirect:/boards/readall";
    }

    // Delete function
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/boards/readall";
    }

}
