package com.springboot.board.service;

import com.springboot.board.repository.BoardRepository;
import com.springboot.board.dto.BoardDTO;
import com.springboot.board.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepository repository;
    @Override
    public Long register(BoardDTO dto) {
        log.info(dto);
        Board board=dtoToEntity(dto);
        repository.save(board);
        return board.getBno();
    }
}
