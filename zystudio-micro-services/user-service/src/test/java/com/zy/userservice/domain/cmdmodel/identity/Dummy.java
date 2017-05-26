package com.zy.userservice.domain.cmdmodel.identity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.Assert;

/**
 * @author by zy.
 */
@Configurable
public class Dummy {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        Assert.notNull(userRepository, "UserRepository must not be null!");
        this.userRepository = userRepository;
    }

    public User getUser(){
        User user = userRepository.findByName("zy");
        logger.info("user :{}", user);
        return user;
    }
}
