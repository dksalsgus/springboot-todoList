package com.example.todo.controller;

import com.example.todo.config.jwt.JwtTokenProvider;
import com.example.todo.config.principal.UserPrincipal;
import com.example.todo.config.principal.UserPrincipalService;
import com.example.todo.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class HomeController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserPrincipalService userPrincipalService;

    @GetMapping("")
    public UserPrincipal home(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal != null) {
            return userPrincipal;
        }
        return null;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(Member member) {
        UserDetails findMember = userPrincipalService.loadUserByUsername(member.getMemberId());
        return ResponseEntity.ok(jwtTokenProvider.createToken(findMember.getUsername()));
    }
}
