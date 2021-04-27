package com.example.todo.service;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.todo.Todo;
import com.example.todo.entity.todo.dto.TodoCreateRequest;
import com.example.todo.repository.TodoRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Todo saveTodo(TodoCreateRequest todoCreateRequest) {
        return todoRepository
                .save(new Todo(
                        todoCreateRequest.getMember(),
                        todoCreateRequest.getTodoKind(),
                        todoCreateRequest.getTodoTitle(),
                        todoCreateRequest.getTodoContent()
                ));
    }

    @Transactional(readOnly = true)
    public List<Todo> listTodo(Member member) throws NotFoundException {
        return todoRepository.findAllByMember(member).orElseThrow(() -> new NotFoundException("Not Found TodoList"));
    }

    @Transactional(readOnly = true)
    public Todo detailsTodo(Long todoNo) throws NotFoundException {
        return todoRepository.findById(todoNo).orElseThrow(() -> new NotFoundException("Not Found Todo"));
    }

    @Transactional
    public void updateTodo() {
    }

    @Transactional
    public void deleteTodo() {
    }

}
