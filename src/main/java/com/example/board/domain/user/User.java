package com.example.board.domain.user;

import com.example.board.domain.board.Board;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    private List<Board> boards;

    @Builder
    public User(Long id, String name, String loginId, String password, List<Board> boards) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.boards = boards;
    }
}