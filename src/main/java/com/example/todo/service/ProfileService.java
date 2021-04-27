package com.example.todo.service;

import com.example.todo.entity.profile.Profile;
import com.example.todo.entity.profile.dto.ProfileCreateRequest;
import com.example.todo.entity.profile.dto.ProfileUpdateRequest;
import com.example.todo.repository.ProfileRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile saveProfile(ProfileCreateRequest profileCreateRequest) throws NotFoundException {
        return profileRepository.save(new Profile(profileCreateRequest.getMember(), profileCreateRequest.getProfilePicture(), profileCreateRequest.getProfileNickname()));
    }

    @Transactional(readOnly = true)
    public Profile detailsProfile(Long profileNo) throws NotFoundException {
        return profileRepository.findById(profileNo).orElseThrow(() -> new NotFoundException("Not Found Profile"));
    }

    @Transactional
    public Profile updateProfile(Long profileNo, ProfileUpdateRequest profileUpdateRequest) throws NotFoundException {
        Profile findProfile = profileRepository.findById(profileNo).orElseThrow(() -> new NotFoundException("Not found Profile"));
        Profile updateProfile = findProfile.update(profileUpdateRequest.getProfilePicture(), profileUpdateRequest.getProfileNickname());
        return updateProfile;
    }

    @Transactional
    public void deleteProfile(Long profileNo) {
        profileRepository.deleteById(profileNo);
    }

}