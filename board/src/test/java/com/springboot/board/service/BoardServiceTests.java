package com.springboot.board.service;

import com.springboot.board.dto.BoardDTO;
import com.springboot.board.dto.PageRequestDTO;
import com.springboot.board.dto.PageResultDTO;
import com.springboot.board.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@SpringBootTest
class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    void testRegister() {
        BoardDTO dto = BoardDTO.builder()
                .title("Test.")
                .content("Test...")
                .writerEmail("user55@aaa.com")
                .build();
        Long bno = boardService.register(dto);
    }

    @Test
    void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);
        for (BoardDTO boardDTO :
                result.getDtoList()) {
            System.out.println(boardDTO);
        }
    }

    @Test
    void testGet() {
        Long bno = 2L;
        BoardDTO boardDTO = boardService.get(bno);
        System.out.println(boardDTO);
    }

    @Test
    void testRemove() {
        Long bno = 1L;
        boardService.removeWithReplies(bno);
    }

    @Test
    void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("제목")
                .content("내용")
                .build();
        boardService.modify(boardDTO);
    }
}
