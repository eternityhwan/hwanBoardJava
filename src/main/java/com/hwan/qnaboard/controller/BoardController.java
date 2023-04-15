package com.hwan.qnaboard.controller;

import com.hwan.qnaboard.model.Board;
import com.hwan.qnaboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards") /** 해당 클래스의 요청을 매핑을 정하는 어노테이션 localhost/boards 로 접근해야 이 클래스에 접근한다. */
public class BoardController {

    /** 객체를 자동으로 주입받는 어노테이션 스프링 의존성 주입을 BoardService의 객체를 주입받습니다 */
    @Autowired
    private BoardService boardService;

    /** HTTP GET 요청에 대한 처리를 담당하는 메소드로, "/create" 경로에 대한 GET 요청이 들어오면 moveCreateForm() 메소드가 실행
     * /create" 경로에 대한 GET 요청을 처리하는 메소드로, "articles/create" 뷰를 반환 */
    // Create function
    @GetMapping("/create")
    public String moveCreateForm() {
        return "articles/create";
    }
    /**  HTTP POST 요청에 대한 처리를 담당하는 메소드로, "/create" 경로에 대한 POST 요청이 들어오면 create() 메소드가 실행
    /create" 경로에 대한 POST 요청을 처리하는 메소드로, Board 객체를 생성하여 boardService의 create() 메소드를 호출하고,
     "redirect:/boards/readall"로 리다이렉트 */
    @PostMapping("/create")
    public String create(Board board) {
        boardService.create(board);
        return "redirect:/boards/readall";
    }

    /** "/readall" 경로에 대한 GET 요청을 처리하는 메소드로,
     * 요청 파라미터로부터 page와 size 값을 받아 Pageable 객체를 생성하여
     * boardService의 findAll() 메소드를 호출하여 페이징 처리된 Board 데이터를 조회하고,
     * 조회된 데이터를 View에 전달하기 위해 Model 객체에 데이터를 설정합니다. */
    // Read function
    // 1. Read ALL
    @GetMapping("/readall")
    public String getAllArticles(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {
        // int page와 int size 값을 받아 Pageable 객체를 생성하여

        // 페이지 번호와 페이지 사이즈를 PageRequest 객체로 변환
        Pageable pageable = PageRequest.of(page, size);

        // Board 데이터를 페이징 처리하여 조회
        Page<Board> boardPage = boardService.findAll(pageable);

        // View에 전달할 데이터 설정
        model.addAttribute("boardList", boardPage.getContent());
        model.addAttribute("currentPage", page + 1);
        model.addAttribute("totalPages", boardPage.getTotalPages());
        model.addAttribute("pageSize", size);

        return "articles/board";
    }

    /** HTTP GET 요청에 대한 처리를 담당하는 메소드로, "/update/{id}" 경로에 대한 GET 요청이 들어오면
     * moveUpdateForm() 메소드가 실행됩니다.
     * {id}는 경로 변수(Path Variable)로, 실제로 사용자가 요청한 값으로 대체됩니다. */
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
