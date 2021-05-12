package com.example.todo.entity.member.dto;

import com.example.todo.entity.member.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@ApiModel(value = "로그인 응답")
@AllArgsConstructor
public class MemberLoginResponse {


    @ApiModelProperty(value = "멤버")
    private Member member;

    @ApiModelProperty(value = "토큰")
    private String token;

}
