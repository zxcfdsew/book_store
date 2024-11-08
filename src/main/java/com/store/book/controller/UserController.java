package com.store.book.controller;

import com.store.book.aspect.annotation.UserRegisterValidAop;
import com.store.book.dto.request.ReqLoginDto;
import com.store.book.dto.request.ReqUserRegisterDto;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @UserRegisterValidAop
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid ReqUserRegisterDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        }
        userService.addUser(dto);
        return ResponseEntity.ok().body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody ReqLoginDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors());
        }
        return ResponseEntity.ok().body(userService.getAccessToken(dto));
    }
}
