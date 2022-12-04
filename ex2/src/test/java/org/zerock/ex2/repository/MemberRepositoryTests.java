package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.ex2.entity.Memo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MemberRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    void testInsert() {
        assertNotNull(memoRepository.getClass());
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Memo..." + i).build();
            memoRepository.save(memo);
            assertNotNull(memo);
        });
    }

    @Test
    void testUpdate() {
        Memo memo = Memo.builder().mno(1L).memoText("Update.......1").build();
        System.out.println("1----------------");
        memoRepository.save(memo);
        assertNotNull(memo);
        System.out.println("2----------------");

    }

    @Test
    void testDelete() {
        Long mno = 1L;
        assertNotNull(mno);
        memoRepository.deleteById(mno);
    }

    @Test
    void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        assertNotNull(result);
        System.out.println(result);
        System.out.println("-----------------------");
        System.out.println("Total Pages: " + result.getTotalPages()); //총 몇 페이지
        System.out.println("Total Count: " + result.getTotalElements()); //전체 개수
        System.out.println("Page Number: " + result.getNumber()); //현재 페이지 번호
        System.out.println("Page Size: " + result.getSize()); //페이지당 데이터 개수
        System.out.println("has next page?: " + result.hasNext()); //다음 페이지
        System.out.println("first page?: " + result.isFirst()); //시작 페이지(0)여부
    }

    @Test
    void testPaging() {
        Sort sort = Sort.by("mno").descending();
        assertNotNull(sort);
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Memo> result = memoRepository.findAll(pageable);
        result.get().forEach(System.out::println);
        System.out.println("--------------");
    }

    @Test
    public void testQueryMethods(){
        List<Memo> list=memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for (Memo memo :list) {
            System.out.println(memo);
        }
    }

    @Test
    void testQueryMethodWithPageable(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());

        Page<Memo> result=memoRepository.findByMnoBetween(10L,50L,pageable);

        result.get().forEach(System.out::println);
    }
    @Commit
    @Transactional
    @Test
    void testDeleteQueryMethods(){
        memoRepository.deleteMemoByMnoLessThan(10L);
    }
}