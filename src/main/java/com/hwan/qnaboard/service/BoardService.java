package com.hwan.qnaboard.service;


import com.hwan.qnaboard.domain.Board;
import com.hwan.qnaboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
    }

    public void create(Board board) {
        boardRepository.save(board);
    }

    public void update(Board board) {
        Board savedBoard = boardRepository.findById(board.getId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
        savedBoard.setTitle(board.getTitle());
        savedBoard.setWriter(board.getWriter());
        boardRepository.save(savedBoard);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
