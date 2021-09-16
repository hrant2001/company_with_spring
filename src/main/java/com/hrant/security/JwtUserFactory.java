package com.hrant.security;

import com.hrant.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static UserDetailsImpl create(User user) {
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRoleName())));
    }
}