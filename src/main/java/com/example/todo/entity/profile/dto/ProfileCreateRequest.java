package com.example.todo.entity.profile.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@ApiModel(value = "프로필 생성")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileCreateRequest {

    @ApiModelProperty(value = "프로필 사진")
    private MultipartFile profilePicture;

    @ApiModelProperty(value = "닉네임")
    private String profileNickname;
}
