package com.hr.oauth.service;

import com.hr.oauth.feignclients.UserFeignClient;
import com.hr.oauth.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public User getUserBy(String email) {
        try {
            User user = userFeignClient.getUserByEmail(email).getBody();
            logger.info("User found");
            return user;
        } catch (RuntimeException exception) {
            logger.error("User not found");
            throw new RuntimeException(exception.getMessage());
        }
    }
}
