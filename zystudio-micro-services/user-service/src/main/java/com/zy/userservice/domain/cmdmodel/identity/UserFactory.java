package com.zy.userservice.domain.cmdmodel.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author by zy.
 */
@Component
public class UserFactory {
    @Autowired
    private UserRepository userRepository;

    public User createBy(Long uid){
        return userRepository.valueOf(uid);
    }
}