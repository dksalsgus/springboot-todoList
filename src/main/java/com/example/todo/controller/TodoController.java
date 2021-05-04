package com.example.todo.controller;

import com.example.todo.config.principal.UserPrincipal;
import com.example.todo.entity.todo.Todo;
import com.example.todo.entity.todo.dto.TodoCreateRequest;
import com.example.todo.entity.todo.dto.TodoDTO;
import com.example.todo.entity.todo.dto.TodoUpdateRequest;
import com.example.todo.service.TodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(value = "Todo API")
public class TodoController {

    private final TodoService todoService;

    @ApiOperation(value = "Todo 생성")
    @PostMapping("/todo")
    public ResponseEntity<TodoDTO> createTodo(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody TodoCreateRequest todoCreateRequest) throws NotFoundException {
        Todo savedTodo = todoService.saveTodo(userPrincipal.getUsername(), todoCreateRequest);
        return ResponseEntity.ok(new TodoDTO(savedTodo));
    }

    @ApiOperation(value = "Todo List")
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> listTodo(@AuthenticationPrincipal UserPrincipal userPrincipal) throws NotFoundException {
        List<Todo> todoList = todoService.listTodo(userPrincipal.getUsername());
        return ResponseEntity.ok(todoList);
    }

    @ApiOperation(value = "Todo Update")
    @PatchMapping("/todo/{todoNo}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long todoNo, @RequestBody TodoUpdateRequest todoUpdateRequest) throws NotFoundException {
        Todo updateTodo = todoService.updateTodo(todoNo, todoUpdateRequest);
        return ResponseEntity.ok(new TodoDTO(updateTodo));
    }

    @ApiOperation(value = "Todo Delete")
    @DeleteMapping("/todo/{todoNo}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoNo) throws NotFoundException {
        todoService.deleteTodo(todoNo);
        return ResponseEntity.noContent().build();
    }
}