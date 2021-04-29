package com.example.todo.service;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.todo.Todo;
import com.example.todo.entity.todo.dto.TodoCreateRequest;
import com.example.todo.entity.todo.dto.TodoUpdateRequest;
import com.example.todo.repository.MemberRepository;
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

    private final MemberRepository memberRepository;

    @Transactional
    public Todo saveTodo(String memberId, TodoCreateRequest todoCreateRequest) throws NotFoundException {
        Member findMember = memberRepository.findByMemberId(memberId).orElseThrow(() -> new NotFoundException("Not Found Member"));
        return todoRepository
                .save(new Todo(
                        findMember,
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
    public Todo updateTodo(Long todoNo, TodoUpdateRequest todoUpdateRequest) throws NotFoundException {
        return todoRepository.findById(todoNo)
                .map(t -> t.update(todoUpdateRequest.getTodoKind(), todoUpdateRequest.getTodoTitle(), todoUpdateRequest.getTodoContent()))
                .orElseThrow(() -> new NotFoundException("Not found Todo"));
    }

    @Transactional
    public void deleteTodo(Long todoNo) {
        todoRepository.deleteById(todoNo);
    }

}
