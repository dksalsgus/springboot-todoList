package com.example.todo.entity.todo;

import com.example.todo.entity.BaseTimeEntity;
import com.example.todo.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "todo_todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @NotNull
    private String todoKind;

    @NotNull
    private String todoTitle;

    private String todoContent;

    public Todo(Member member, String todoKind, String todoTitle, String todoContent) {
        this.member = member;
        this.todoKind = todoKind;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
    }


    public Todo update(String todoKind, String todoTitle, String todoContent) {
        this.todoKind = todoKind;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        return this;
    }
}
