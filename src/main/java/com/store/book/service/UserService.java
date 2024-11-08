package com.store.book.service;

import com.store.book.dto.request.ReqSignupDto;
import com.store.book.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public int addUser(ReqSignupDto dto) {
        return userMapper.addUser(dto.toEntity(passwordEncoder));
    }

    public boolean checkDuplicateUsername(String username) {
        return userMapper.checkUsername(username) > 0;
    }

    public boolean checkDuplicateEmail(String email) {
        return userMapper.checkEmail(email) > 0;
    }
}
