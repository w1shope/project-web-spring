package com.example.board.controller.board;

import com.example.board.domain.board.Board;
import com.example.board.domain.user.User;
import com.example.board.dto.board.BoardEnrolDto;
import com.example.board.service.board.BoardEnrolService;
import com.example.board.service.board.BoardListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardEnrolController {

    private final BoardEnrolService enrolService;
    private final BoardListService boardListService;

    @GetMapping("/enrol")
    public String openBoardEnrol(@ModelAttribute Board board, HttpSession session, Model model)
    {
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser != null)
            model.addAttribute("loginUser", loginUser);
        log.info("loginUser={}", loginUser);
        return "/write";
    }

    @PostMapping("/enrol")
    public String insertBoardEnrol(BoardEnrolDto boardEnrolDto, @ModelAttribute User loginUser, Model model, RedirectAttributes redirectAttributes) {
        Long enrolBoardId = enrolService.enrolBoard(boardEnrolDto, loginUser);
        Board findBoard = boardListService.findById(enrolBoardId);
        model.addAttribute("board", findBoard);
        redirectAttributes.addAttribute("id", enrolBoardId);
        return "redirect:/boards/{id}";
    }

}
