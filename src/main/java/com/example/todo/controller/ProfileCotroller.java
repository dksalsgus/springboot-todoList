package com.example.todo.controller;

import com.example.todo.entity.profile.Profile;
import com.example.todo.entity.profile.dto.ProfileCreateRequest;
import com.example.todo.entity.profile.dto.ProfileDTO;
import com.example.todo.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "프로필 API")
public class ProfileCotroller {

    private final ProfileService profileService;

    @PostMapping("{memberId}/profile")
    @ApiOperation(value = "프로필 생성")
    public ResponseEntity<ProfileDTO> saveProfile(@PathVariable String memberId, @RequestBody ProfileCreateRequest profileCreateRequest) throws NotFoundException {
        Profile saveProfile = profileService.saveProfile(memberId, profileCreateRequest);
        return ResponseEntity.ok(new ProfileDTO(saveProfile));
    }
}
