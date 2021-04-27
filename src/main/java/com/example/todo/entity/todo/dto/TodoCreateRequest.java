package com.example.todo.entity.todo.dto;

import com.example.todo.entity.member.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "Todo 생성 요청")
public class TodoCreateRequest {

    @ApiModelProperty(value = "회원")
    private Member member;

    @ApiModelProperty(value = "종류")
    private String todoKind;

    @ApiModelProperty(value = "제목")
    private String todoTitle;

    @ApiModelProperty(value = "내용")
    private String todoContent;

}
