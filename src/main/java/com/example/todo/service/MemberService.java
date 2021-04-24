package com.example.todo.service;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.member.dto.MemberJoinRequest;
import com.example.todo.entity.member.dto.MemberUpdateRequest;
import com.example.todo.repository.MemberRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Member saveMember(MemberJoinRequest memberJoinRequest) {
        return memberRepository.save(
                new Member(
                        memberJoinRequest.getMemberId(),
                        passwordEncoder.encode(memberJoinRequest.getMemberPw()),
                        memberJoinRequest.getMemberName(),
                        memberJoinRequest.getMemberGender(),
                        memberJoinRequest.getMemberEmail()
                ));
    }

    @Transactional(readOnly = true)
    public Member detalisMember(Long memberNo) throws NotFoundException {
        return memberRepository.findById(memberNo).orElseThrow(() -> new NotFoundException("Not Found Member"));
    }

    @Transactional
    public Member updateMember(Long memberNo, MemberUpdateRequest memberUpdateRequest) throws NotFoundException {
        Member findMember = memberRepository.findById(memberNo).orElseThrow(() -> new NotFoundException("Not Found Member"));
        findMember.update(memberUpdateRequest.getMemberPw(), memberUpdateRequest.getMemberName(), memberUpdateRequest.getMemberGender(), memberUpdateRequest.getMemberEmail());
        return memberRepository.save(findMember);
    }

    @Transactional
    public void deleteMember(Long memberNo) {
        memberRepository.deleteById(memberNo);
    }


}
