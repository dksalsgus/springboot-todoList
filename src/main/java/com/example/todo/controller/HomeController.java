package com.example.todo.controller;

import com.example.todo.config.principal.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public ResponseEntity<UserPrincipal> home(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal != null) {
            return ResponseEntity.ok(userPrincipal);
        }
        return ResponseEntity.ok(null);
    }
}
