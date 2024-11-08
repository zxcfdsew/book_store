package com.store.book.service;

import com.store.book.dto.request.ReqLoginDto;
import com.store.book.dto.request.ReqUserRegisterDto;
import com.store.book.entity.User;
import com.store.book.repository.UserMapper;
import com.store.book.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public int addUser(ReqUserRegisterDto dto) {
        return userMapper.addUser(dto.toEntity(passwordEncoder));
    }

    public String getAccessToken(ReqLoginDto dto) {
        User user = userMapper.findByUsername(dto.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException("사용자 정보를 다시 확인하세요");
        }

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("사용자 정보를 다시 확인하세요");
        }

        return jwtProvider.generateAccessToken(user);
    }

    public boolean checkDuplicateUsername(String username) {
        return userMapper.checkUsername(username) > 0;
    }

    public boolean checkDuplicateEmail(String email) {
        return userMapper.checkEmail(email) > 0;
    }
}
