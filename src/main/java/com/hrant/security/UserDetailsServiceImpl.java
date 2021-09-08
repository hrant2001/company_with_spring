package com.hrant.security;

import com.hrant.model.User;
import com.hrant.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(userName).orElseThrow(() ->
                    new UsernameNotFoundException("User doesn't exists"));
            return new UserDetailsImpl(user);
        } catch (UsernameNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }
}
