package com.example.todo.controller;

import com.example.todo.config.jwt.JwtTokenProvider;
import com.example.todo.config.principal.UserPrincipal;
import com.example.todo.entity.member.Member;
import com.example.todo.entity.member.dto.MemberLoginRequest;
import com.example.todo.entity.member.dto.MemberLoginResponse;
import com.example.todo.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Api(value = "Home")
public class HomeController {

    private final MemberService memberService;

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("")
    @ApiOperation(value = "Home")
    public UserPrincipal home(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal != null) {
            return userPrincipal;
        }
        return null;
    }

    @PostMapping("/loginPost")
    @ApiOperation(value = "로그인")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody MemberLoginRequest memberLoginRequest) throws NotFoundException {
        Member loginMember = memberService.login(memberLoginRequest);
        MemberLoginResponse memberLoginResponse = new MemberLoginResponse(loginMember, jwtTokenProvider.createToken(loginMember.getMemberId()));
        return ResponseEntity.ok(memberLoginResponse);
    }

}
