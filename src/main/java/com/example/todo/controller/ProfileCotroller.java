package com.example.todo.controller;

import com.example.todo.config.principal.UserPrincipal;
import com.example.todo.entity.profile.Profile;
import com.example.todo.entity.profile.dto.ProfileCreateRequest;
import com.example.todo.entity.profile.dto.ProfileDTO;
import com.example.todo.entity.profile.dto.ProfileUpdateRequest;
import com.example.todo.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Api(value = "프로필 API")
public class ProfileCotroller {

    private final ProfileService profileService;

    @PostMapping("/profile")
    @ApiOperation(value = "프로필 생성")
    public ResponseEntity<ProfileDTO> saveProfile(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody ProfileCreateRequest profileCreateRequest, @RequestParam("profilePicture") MultipartFile multipartFile) throws NotFoundException {
        profileCreateRequest.setProfilePicture(multipartFile.getOriginalFilename());
        Profile saveProfile = profileService.saveProfile(userPrincipal.getUsername(),profileCreateRequest);
        return ResponseEntity.ok(new ProfileDTO(saveProfile));
    }

    @GetMapping("/profile/{profileNo}")
    @ApiOperation(value = "프로필 조회")
    public ResponseEntity<ProfileDTO> detailsProfile(@PathVariable Long profileNo) throws NotFoundException {
        Profile findProfile = profileService.detailsProfile(profileNo);
        return ResponseEntity.ok(new ProfileDTO(findProfile));
    }

    @PatchMapping("/profile/{profileNo}")
    @ApiOperation(value = "프로필 수정")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long profileNo, @RequestBody ProfileUpdateRequest profileUpdateRequest) throws NotFoundException {
        Profile updateProfile = profileService.updateProfile(profileNo, profileUpdateRequest);
        return ResponseEntity.ok(new ProfileDTO(updateProfile));
    }

    @DeleteMapping("/profile/{profileNo}")
    @ApiOperation(value = "프로필 삭제")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileNo) {
        profileService.deleteProfile(profileNo);
        return ResponseEntity.noContent().build();
    }
}
