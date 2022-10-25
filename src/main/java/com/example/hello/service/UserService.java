package com.example.hello.service;

import com.example.hello.DataNotFoundException;
import com.example.hello.data.User;
import com.example.hello.data.UserDto;
import com.example.hello.mapper.UserMapper;
import com.example.hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto create(String username, String email, String password) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setPassword(passwordEncoder.encode(password));
        userRepository.save(UserMapper.MAPPER.toEntity(userDto));
        return userDto;
    }

    public UserDto getUser(String username) {
        Optional<User> user = userRepository.findByusername(username);
        if (user.isPresent()) {
            return UserMapper.MAPPER.toDto(user.get());
        } else {
            throw new DataNotFoundException("user not found");
        }
    }
}
