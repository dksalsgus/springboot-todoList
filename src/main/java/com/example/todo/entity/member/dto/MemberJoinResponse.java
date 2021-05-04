package com.example.todo.entity.member.dto;

import com.example.todo.entity.member.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberJoinResponse {

    @ApiModelProperty(value = "회원")
    private Member member;

    public MemberJoinResponse(Member member) {
        this.member = member;
    }
}
