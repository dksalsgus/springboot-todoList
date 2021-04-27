package com.example.todo.repository;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<List<Todo>> findAllByMember(Member member);
}
