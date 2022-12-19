package com.example.club.security;

import com.example.club.entity.ClubMember;
import com.example.club.entity.ClubMemberRole;
import com.example.club.repository.ClubMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class ClubMemberTests {
    @Autowired
    private ClubMemberRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void insertDummies() {
        //1-80까지는 User만 지정
        //81-90까지는 User,Manager
        //91-100까지는 User,Manager,Admin
        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@example.com")
                    .name("사용자" + i)
                    .fromSocial(false)
                    .roleSet(new HashSet<>())
                    .password(passwordEncoder.encode("1111")).build();
            clubMember.addMemberRole(ClubMemberRole.USER);
            if (i > 80) {
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if (i > 90) {
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
            repository.save(clubMember);
        });
    }

    @Test
    void testRead() {
        Optional<ClubMember> optional = repository.findByEmail("user95@example.com", false);
        ClubMember clubMember = optional.orElse(null);
        System.out.println(clubMember);
    }


}

