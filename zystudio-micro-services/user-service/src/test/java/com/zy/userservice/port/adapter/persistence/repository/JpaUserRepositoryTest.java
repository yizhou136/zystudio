package com.zy.userservice.port.adapter.persistence.repository;

import com.zy.userservice.AbstractSpringBootTest;
import com.zy.userservice.domain.cmdmodel.identity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by zy.
 */

public class JpaUserRepositoryTest extends AbstractSpringBootTest{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLoadUser(){
        User user = null;//new User();
        user.setName("zy");
        user.setHeadface("http://www.163.com/13451");
        user.setPassword("123");
        UserDetail userDetail = new UserDetail();
        userDetail.setAge(12);
        userDetail.setEmailAddress(new EmailAddress("yizhou136@163.com"));
        userDetail.setFullName(new FullName("yi", "zhou"));
        userDetail.setTelephone(new Telephone("13442321223"));
        user.setUserDetail(userDetail);
        //user = userRepository.save(user);
        logger.info("testLoadUser user:{} userDetail:{}",
                user.getUid(), userDetail);
    }
}