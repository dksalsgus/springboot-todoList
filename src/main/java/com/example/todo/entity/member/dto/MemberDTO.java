package com.example.todo.entity.member.dto;

import com.example.todo.entity.member.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
public class MemberDTO {

    @ApiModelProperty(value = "아이디")
    private String memberId;

    @ApiModelProperty(value = "비밀번호")
    private String memberPw;

    @ApiModelProperty(value = "이름")
    private String memberName;

    @ApiModelProperty(value = "성병")
    private String memberGender;

    @ApiModelProperty(value = "이메일")
    private String memberEmail;

    public MemberDTO(Member member) {
        BeanUtils.copyProperties(member, this);
    }

}
