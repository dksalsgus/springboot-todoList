package com.example.todo.entity.member.dto;

import lombok.Getter;

@Getter
public class MemberJoinRequest {

    private String memberId;

    private String memberPw;

    private String memberName;

    private String memberGender;

    private String memberEmail;
}
