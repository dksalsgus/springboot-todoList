package com.example.todo.entity.profile.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ApiModel(value = "프로필 수정 요청")
public class ProfileUpdateRequest {

    @ApiModelProperty(value = "프로필 사진 경로")
    private MultipartFile profilePicture;

    @ApiModelProperty(value = "닉네임")
    private String profileNickname;

}
