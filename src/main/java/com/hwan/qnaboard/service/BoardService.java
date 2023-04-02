package com.hwan.qnaboard.service;


import com.hwan.qnaboard.model.Board;
import com.hwan.qnaboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    // Create
    public void create(Board board)
    {
        boardRepository.save(board);
    }

    // ReadAll
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // Read
    public Board findById(Long id) {
        return boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
    }

    // Update
    public void update(Board board) {
        Board savedBoard = boardRepository.findById(board.getId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));
        savedBoard.setTitle(board.getTitle());
        savedBoard.setWriter(board.getWriter());
        boardRepository.save(savedBoard);
    }

    // Delete
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
