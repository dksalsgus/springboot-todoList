package com.example.todo.entity.profile.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value = "프로필 생성")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileCreateRequest {

    @ApiModelProperty(value = "프로필 사진")
    private String profilePicture;

    @ApiModelProperty(value = "닉네임")
    private String profileNickname;
}
