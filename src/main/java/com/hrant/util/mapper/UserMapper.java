package com.hrant.util.mapper;

import com.hrant.dto.UserDto;
import com.hrant.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDto> {
    @Autowired
    UserMapper() {
        super(User.class, UserDto.class);
    }
}
