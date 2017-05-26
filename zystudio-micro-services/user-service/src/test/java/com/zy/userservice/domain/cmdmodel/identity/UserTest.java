package com.zy.userservice.domain.cmdmodel.identity;

import com.zy.userservice.AbstractSpringBootTest;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by zy.
 */
public class UserTest extends AbstractSpringBootTest{

    @Autowired
    private UserFactory userFactory;

    @Test
    public void testRegisterUser(){
        UserDetail userDetail = new UserDetail();
        userDetail.setTelephone(new Telephone("1132421341234"));
        userDetail.setEmailAddress(new EmailAddress("yizhou136@163.com"));
        userDetail.setAge(11);
        userDetail.setFullName(new FullName("zy","zhou"));
        User user = new User("zy","pwd","http://www.163.com/img/adfa123134",
                userDetail);
        user.registeUser();
    }

    public void testUserLogin(){

    }

    @Test
    public void testValueOf(){
        User user = userFactory.createBy(Long.valueOf(12));
        assertNotNull(user);
        logger.info("userFactory valueOf {}", user);
    }

    @Test
    public void testConfigurable(){
        //testRegisterUser();
        Dummy dummy = new Dummy();
        logger.info("dummy :{}", dummy);
        User user = dummy.getUser();
        assertNotNull(user);


        UserEntityListener userEntityListener = new UserEntityListener();
        userEntityListener.prePersist("hahah");
    }
}
