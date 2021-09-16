package com.hrant.service;

import com.hrant.dto.EmployeeDto;
import com.hrant.dto.UserDto;
import com.hrant.model.Employee;
import com.hrant.model.User;
import com.hrant.repository.RoleRepository;
import com.hrant.repository.UserRepository;
import com.hrant.util.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto findUserByUsername(String username) throws NoSuchElementException {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            LOGGER.warn("The user with the username " + username + " was not found");
            throw new NoSuchElementException();
        }
        return userMapper.toDto(user);
    }
}
