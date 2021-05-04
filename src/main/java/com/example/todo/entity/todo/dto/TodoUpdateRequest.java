package com.example.todo.entity.todo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@ApiModel(value = "Todo 수정 요청")
public class TodoUpdateRequest {

    @ApiModelProperty(value = "종류")
    private String todoKind;

    @ApiModelProperty(value = "제목")
    private String todoTitle;

    @ApiModelProperty(value = "내용")
    private String todoContent;

}
