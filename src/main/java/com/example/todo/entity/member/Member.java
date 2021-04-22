package com.example.todo.entity.member;

import com.example.todo.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "todo_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(unique = true)
    @NotNull
    private String memberId;

    @NotNull
    private String memberPw;

    private String memberName;

    private String memberGender;

    private String memberEmail;

    public Member(String memberId, String memberPw, String memberName, String memberGender, String memberEmail) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberGender = memberGender;
        this.memberEmail = memberEmail;
    }
}
