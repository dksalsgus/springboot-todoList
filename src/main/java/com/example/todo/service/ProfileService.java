package com.example.todo.service;

import com.example.todo.entity.member.Member;
import com.example.todo.entity.profile.Profile;
import com.example.todo.entity.profile.dto.ProfileCreateRequest;
import com.example.todo.entity.profile.dto.ProfileUpdateRequest;
import com.example.todo.repository.MemberRepository;
import com.example.todo.repository.ProfileRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public Profile saveProfile(String memberId, ProfileCreateRequest profileCreateRequest) throws NotFoundException {
        Member findMember = memberRepository.findByMemberId(memberId).orElseThrow(() -> new NotFoundException("Not Found Member"));
        return profileRepository
                .save(new Profile(
                        findMember,
                        profileCreateRequest.getProfilePicture().getOriginalFilename(),
                        profileCreateRequest.getProfileNickname()
                ));
    }

    @Transactional(readOnly = true)
    public Profile detailsProfile(String memberId) throws NotFoundException {
        Member findMember = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException("Not Found Member"));
        return profileRepository.findByMember(findMember)
                .orElseThrow(() -> new NotFoundException("Not Found Profile"));
    }

    @Transactional
    public Profile updateProfile(Long profileNo, ProfileUpdateRequest profileUpdateRequest) throws NotFoundException {
        Profile findProfile = profileRepository.findById(profileNo)
                .orElseThrow(() -> new NotFoundException("Not found Profile"));

        String oldFileName = findProfile.getMember().getMemberId() + "_" + findProfile.getProfilePicture();
        String oldfilePath = System.getProperty("user.dir") + "\\profilePicture\\" + oldFileName;
        String updateFileName = findProfile.getMember().getMemberId() + "_update_" + profileUpdateRequest.getProfilePicture().getOriginalFilename();
        String updateFilePath = System.getProperty("user.dir") + "\\profilePicture\\" + updateFileName;
        File oldFile = new File(oldfilePath);
        File updateFile = new File(updateFilePath);
        if (oldFile.exists()) {
            oldFile.delete();
            try {
                profileUpdateRequest.getProfilePicture().transferTo(updateFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Profile updateProfile = findProfile.update(updateFileName, profileUpdateRequest.getProfileNickname());
        return profileRepository.save(updateProfile);
    }

    @Transactional
    public void deleteProfile(Long profileNo) throws NotFoundException {
        Profile findProfile = profileRepository.findById(profileNo)
                .orElseThrow(() -> new NotFoundException("Not Found Profile"));

        String deleteFileName = findProfile.getMember().getMemberId() + "_" + findProfile.getProfilePicture();
        String deleteFilePath = System.getProperty("user.dir") + "\\profilePicture\\" + deleteFileName;
        File deleteFile = new File(deleteFilePath);
        deleteFile.delete();
        profileRepository.deleteById(profileNo);
    }

    public File uploadImage(String memberId, MultipartFile profileImg) throws NotFoundException {
        if (memberId == null) throw new NotFoundException("Not Found Member");
        String fileName = memberId + "_" + profileImg.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + "\\profilePicture\\" + fileName;
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            try {
                saveFile.mkdirs();
                System.out.println("폴더 생성");
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return saveFile;
    }

}
