package com.springboot.board.controller;

import com.springboot.board.dto.BoardDTO;
import com.springboot.board.dto.PageRequestDTO;
import com.springboot.board.dto.PageResultDTO;
import com.springboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list........."+pageRequestDTO);
        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }
    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }
    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);
        Long bno=boardService.register(dto);
        log.info("BNO: "+bno);
        redirectAttributes.addFlashAttribute("msg",bno);
        return "redirect:/board/list";
    }
    @GetMapping({"/read","/modify"})
    public void read(PageRequestDTO pageRequestDTO, Long bno, Model model) {
        log.info("bno: "+bno);
        BoardDTO boardDTO = boardService.get(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {
        log.info("bno: "+bno);
        boardService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("post modify...................");
        log.info("dto: "+dto);
        boardService.modify(dto);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type",pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword",pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("bno",dto.getBno());
        return "redirect:/board/read";
    }
}
