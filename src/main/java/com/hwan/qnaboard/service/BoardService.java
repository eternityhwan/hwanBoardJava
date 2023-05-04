package com.hwan.qnaboard.service;


import com.hwan.qnaboard.entity.Board;
import com.hwan.qnaboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    // Create
    public void create(Board board)
    {
        boardRepository.save(board);
    }

    // Only ReadAll
//    public List<Board> findAll() {
//        return boardRepository.findAll();
//    }

    // ReadAll with paging fucntion
    // Pageable은 Spring Data JPA에서 제공하는 페이징 처리 인터페이스
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
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
