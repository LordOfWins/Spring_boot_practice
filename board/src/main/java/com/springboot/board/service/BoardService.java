package com.springboot.board.service;

import com.springboot.board.dto.BoardDTO;
import com.springboot.board.entity.Board;
import com.springboot.board.entity.Member;

public interface BoardService {
    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto){
        Member member= Member.builder().email(dto.getWriterEmail()).build();

        return Board.builder().bno(dto.getBno()).title(dto.getTitle())
                .content(dto.getContent()).writer(member).build();
    }

}
