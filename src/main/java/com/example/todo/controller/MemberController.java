package com.example.todo.controller;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.member.dto.MemberDTO;
import com.example.todo.entity.member.dto.MemberJoinRequest;
import com.example.todo.entity.member.dto.MemberJoinResponse;
import com.example.todo.service.MemberService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<MemberJoinResponse> saveMember(@RequestBody MemberJoinRequest memberJoinRequest) {
        Member saveMember = memberService.saveMember(memberJoinRequest);
        return ResponseEntity.ok(new MemberJoinResponse(saveMember));
    }

    @GetMapping(value = "/member/{memberNo}")
    public ResponseEntity<MemberDTO> detailsMember(@PathVariable Long memberNo) throws NotFoundException {
        Member findMember = memberService.detalisMember(memberNo);
        return ResponseEntity.ok(new MemberDTO(findMember));
    }
}
