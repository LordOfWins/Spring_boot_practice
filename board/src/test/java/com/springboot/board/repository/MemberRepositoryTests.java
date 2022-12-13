package com.springboot.board.repository;

import com.springboot.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertMembers() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@aaa.com")
                    .password("1111")
                    .name("User" + i)
                    .build();
            memberRepository.save(member);
        });
    }
}
