package com.example.todo.controller;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.member.dto.MemberDTO;
import com.example.todo.entity.member.dto.MemberJoinRequest;
import com.example.todo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<MemberDTO> saveMember(@RequestBody MemberJoinRequest memberJoinRequest) {
        Member saveMember = memberService.saveMember(memberJoinRequest);
        return ResponseEntity.ok(new MemberDTO(saveMember));
    }
}
