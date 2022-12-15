package com.springboot.board.repository;

import com.springboot.board.entity.Board;
import com.springboot.board.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long bno = (long) (Math.random() * 100) + 1;
            Board board = Board.builder().bno(bno).build();
            Reply reply = Reply.builder().text("Reply...." + i).board(board)
                    .replier("guest")
                    .build();
            replyRepository.save(reply);
        });
    }

    @Test
    void readReply() {
        //낭비스러운 코드가 될 수 있음
        Optional<Reply> result = replyRepository.findById(1L);
        Reply reply = result.orElseThrow();


        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    @Test
    public void testListByBoard() {
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(90L).build());
        replyList.forEach(reply -> System.out.println(reply));
    }

}
