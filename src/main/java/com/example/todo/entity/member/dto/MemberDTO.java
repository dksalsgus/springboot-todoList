package com.example.todo.entity.member.dto;

import com.example.todo.entity.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class MemberDTO {

    private String memberId;

    private String memberPw;

    private String memberName;

    private String memberGender;

    private String memberEmail;

    public MemberDTO(Member member) {
        BeanUtils.copyProperties(member, this);
    }

}
