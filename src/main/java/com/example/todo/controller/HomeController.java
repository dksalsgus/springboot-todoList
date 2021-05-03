package com.example.todo.controller;

import com.example.todo.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class HomeController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;

    @GetMapping("")
    public void home() {
    }

    @PostMapping("login")
    public ResponseEntity<String> login(String memberId, String memberPw) {
        UserDetails findMember = userDetailsService.loadUserByUsername(memberId);
        return ResponseEntity.ok(jwtTokenProvider.createToken(findMember.getUsername()));
    }
}
