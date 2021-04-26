package com.example.todo.controller;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.member.dto.MemberDTO;
import com.example.todo.entity.member.dto.MemberJoinRequest;
import com.example.todo.entity.member.dto.MemberJoinResponse;
import com.example.todo.entity.member.dto.MemberUpdateRequest;
import com.example.todo.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "Member API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<MemberJoinResponse> saveMember(@RequestBody MemberJoinRequest memberJoinRequest) {
        Member saveMember = memberService.saveMember(memberJoinRequest);
        return ResponseEntity.ok(new MemberJoinResponse(saveMember));
    }

    @GetMapping(value = "/member/{memberNo}")
    @ApiOperation(value = "회원 조회")
    public ResponseEntity<MemberDTO> detailsMember(@PathVariable Long memberNo) throws NotFoundException {
        Member findMember = memberService.detalisMember(memberNo);
        return ResponseEntity.ok(new MemberDTO(findMember));
    }

    @PatchMapping("member/{memberNo}")
    @ApiOperation(value = "회원 수정")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long memberNo, @RequestBody MemberUpdateRequest memberUpdateRequest) throws NotFoundException {
        Member updateMember = memberService.updateMember(memberNo, memberUpdateRequest);
        return ResponseEntity.ok(new MemberDTO(updateMember));
    }

    @DeleteMapping("member/{memberNo}")
    @ApiOperation(value = "회원 삭제")
    public ResponseEntity<Object> deleteMember(@PathVariable Long memberNo) {
        memberService.deleteMember(memberNo);
        return ResponseEntity.noContent().build();
    }
}
