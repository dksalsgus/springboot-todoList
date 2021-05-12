package com.example.todo.entity.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@ApiModel(value = "로그인 요청")
public class MemberLoginRequest {

    @ApiModelProperty(value = "아이디")
    private String memberId;

    @ApiModelProperty(value = "비밀번호")
    private String memberPw;
}
