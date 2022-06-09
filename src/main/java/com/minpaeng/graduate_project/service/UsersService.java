package com.minpaeng.graduate_project.service;

import com.minpaeng.graduate_project.domain.Users;
import com.minpaeng.graduate_project.domain.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public String userName(String id) {
        Users user = usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("유저 아이디 존재하지 않음.")
        );
        return user.getName();
    }
}
