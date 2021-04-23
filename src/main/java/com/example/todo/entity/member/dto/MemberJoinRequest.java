package com.example.todo.entity.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberJoinRequest {

    @ApiModelProperty(value = "아이디")
    private String memberId;

    @ApiModelProperty(value = "비밀번호")
    private String memberPw;

    @ApiModelProperty(value = "이름")
    private String memberName;

    @ApiModelProperty(value = "성별")
    private String memberGender;

    @ApiModelProperty(value = "이메일")
    private String memberEmail;

}
