package com.example.todo.controller;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.todo.Todo;
import com.example.todo.entity.todo.dto.TodoCreateRequest;
import com.example.todo.entity.todo.dto.TodoDTO;
import com.example.todo.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(value = "Todo API")
public class TodoController {

    private final TodoService todoService;

    @ApiOperation(value = "Todo 생성")
    @PostMapping("/todo")
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoCreateRequest todoCreateRequest) {
        Todo savedTodo = todoService.saveTodo(todoCreateRequest);
        return ResponseEntity.ok(new TodoDTO(savedTodo));
    }

    @ApiOperation(value = "Todo List")
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> listTodo(Member member) throws NotFoundException {
        List<Todo> todoList = todoService.listTodo(member);
        return ResponseEntity.ok(todoList);
    }

    @ApiOperation(value = "Todo Update")
    @PatchMapping("/todo/{todoNo}")
    public void updateTodo(@PathVariable Long todoNo) {

    }
}