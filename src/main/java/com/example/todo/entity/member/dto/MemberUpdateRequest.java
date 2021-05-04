package com.example.todo.entity.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberUpdateRequest {

    @ApiModelProperty(value = "비밀번호")
    private String memberPw;

    @ApiModelProperty(value = "이름")
    private String memberName;

    @ApiModelProperty(value = "성병")
    private String memberGender;

    @ApiModelProperty(value = "이메일")
    private String memberEmail;

}
