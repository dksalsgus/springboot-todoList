package com.example.todo.entity.profile.dto;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.profile.Profile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ProfileDTO {

    private Member member;

    private String profilePicture;

    private String profileNickname;

    public ProfileDTO(Profile profile) {
        BeanUtils.copyProperties(profile, this);
    }
}
