package com.example.todo.entity.member;

import com.example.todo.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Table(name = "todo_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(unique = true)
    @NotNull
    private String memberId;

    @NotNull
    private String memberPw;

    private String memberName;

    private String memberGender;

    private String memberEmail;

    public Member update(String memberPw, String memberName, String memberGender, String memberEmail) {
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberGender = memberGender;
        this.memberEmail = memberEmail;
        return this;
    }

    public Member(String memberId, String memberPw, String memberName, String memberGender, String memberEmail) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberGender = memberGender;
        this.memberEmail = memberEmail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return memberPw;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
