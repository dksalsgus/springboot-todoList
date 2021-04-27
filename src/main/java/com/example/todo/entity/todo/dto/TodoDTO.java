package com.example.todo.entity.todo.dto;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.todo.Todo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

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

    public List<Todo> TodoDTO(List<Todo> todoList) {
        List<Todo> list = new ArrayList<>();
        for (Todo todo : todoList) {
            BeanUtils.copyProperties(todoList, this);
            list.add(todo);
        }
        return list;
    }
}
