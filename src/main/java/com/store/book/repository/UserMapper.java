package com.store.book.repository;

import com.store.book.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int addUser(User user);
    int checkUsername(String username);
    int checkEmail(String email);
    User findByUsername(String username);
}
