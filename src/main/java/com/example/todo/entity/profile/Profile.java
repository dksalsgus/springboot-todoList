package com.example.todo.entity.profile;

import com.example.todo.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "todo_profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    private String profilePicture;

    private String profileNickname;

    public Profile(Member member, String profilePicture, String profileNickname) {
        this.member = member;
        this.profilePicture = profilePicture;
        this.profileNickname = profileNickname;
    }
}
