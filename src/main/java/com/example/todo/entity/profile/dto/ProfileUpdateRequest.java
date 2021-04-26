package com.example.todo.entity.profile.dto;

import com.example.todo.entity.member.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "프로필 수정 요청")
public class ProfileUpdateRequest {

    @ApiModelProperty(value = "회원")
    private Member member;

    @ApiModelProperty(value = "프로필 사진 경로")
    private String profilePicture;

    @ApiModelProperty(value = "닉네임")
    private String profileNickname;

}
