package com.example.todo.service;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.profile.Profile;
import com.example.todo.entity.profile.dto.ProfileCreateRequest;
import com.example.todo.repository.MemberRepository;
import com.example.todo.repository.ProfileRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Profile saveProfile(String userId, ProfileCreateRequest profileCreateRequest) throws NotFoundException {
        Member findMember = memberRepository.findByMemberId(userId).orElseThrow(() -> new NotFoundException("Not Found Member"));
        profileCreateRequest.setMember(findMember);
        return profileRepository.save(new Profile(profileCreateRequest.getMember(), profileCreateRequest.getProfilePicture(), profileCreateRequest.getProfileNickname()));
    }
}
