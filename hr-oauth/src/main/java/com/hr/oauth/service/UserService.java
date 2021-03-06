package com.hr.oauth.service;

import com.hr.oauth.feignclients.UserFeignClient;
import com.hr.oauth.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userFeignClient.getUserByEmail(username).getBody();
            logger.info("User found");
            return user;
        } catch (RuntimeException exception) {
            logger.error("User not found");
            throw new UsernameNotFoundException(exception.getMessage());
        }
    }

}
