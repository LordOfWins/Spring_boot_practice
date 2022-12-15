package com.springboot.board.repository;

import com.springboot.board.entity.Board;
import com.springboot.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying
    @Query("delete from Reply r where r.board.bno=:bno")
    void deleteByBno(Long bno);
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}
