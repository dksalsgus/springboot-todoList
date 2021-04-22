package com.example.todo.entity.todo.dto;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.todo.Todo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class TodoDTO {

    private Member member;

    private String todoKind;

    private String todoTitle;

    private String todoContent;

    public TodoDTO(Todo todo) {
        BeanUtils.copyProperties(todo, this);
    }
}
