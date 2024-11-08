package com.store.book.controller;

import com.store.book.aspect.annotation.SignupValidAop;
import com.store.book.dto.request.ReqSigninDto;
import com.store.book.dto.request.ReqSignupDto;
import com.store.book.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @SignupValidAop
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid ReqSignupDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        }
        userService.addUser(dto);
        return ResponseEntity.ok().body("회원가입 완료");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody ReqSigninDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        }
        return ResponseEntity.ok().body(userService.getAccessToken(dto));
    }
}
