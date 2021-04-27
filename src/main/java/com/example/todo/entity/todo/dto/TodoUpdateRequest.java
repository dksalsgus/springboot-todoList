package com.example.todo.entity.todo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@ApiModel(value = "Todo 수정 요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoUpdateRequest {

    @ApiModelProperty(value = "종류")
    private String todoKind;

    @ApiModelProperty(value = "제목")
    private String todoTitle;

    @ApiModelProperty(value = "내용")
    private String todoContent;

}
