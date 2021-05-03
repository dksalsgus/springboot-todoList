package com.example.todo.controller;

import com.example.todo.config.jwt.JwtTokenProvider;
import com.example.todo.entity.member.Member;
import com.example.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final JwtTokenProvider jwtTokenProvider;

    private final MemberRepository memberRepository;

    @GetMapping("")
    public ResponseEntity<Member> home(@AuthenticationPrincipal Member member) {
        if (member != null) {
            return ResponseEntity.ok(member);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("login")
    public String login(@RequestBody Map<String, String> member) {
        Member findMember = memberRepository.findByMemberId(member.get("memberId"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디 입니다"));
        return jwtTokenProvider.createToken(findMember.getUsername());
    }
}
