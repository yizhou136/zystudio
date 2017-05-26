package com.zy.userservice.application;


import com.zy.userservice.AbstractSpringBootTest;
import com.zy.userservice.application.command.UserLoginCommand;
import com.zy.userservice.application.exception.UserNotExistsException;
import com.zy.userservice.application.exception.WrongPasswordException;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.core.Is.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by zy.
 */
public class IdentityCmdServiceTest extends AbstractSpringBootTest{

    @Autowired
    private IdentityCmdService identityCmdService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testUserLogin(){
        UserLoginCommand userLoginCommand = new UserLoginCommand("notexists","123");
        boolean ret = false;
        logger.info("testUserLogin test UserNotExistsException");
        //thrown.expect(UserNotExistsException.class);
        try {
            ret = identityCmdService.login(userLoginCommand);
        }catch (UserNotExistsException e){
            assertThat(e,
                    isA(UserNotExistsException.class));

        }
        //Fail(UserNotExistsException);
        assertFalse(ret);


        logger.info("testUserLogin test WrongPasswordException");
        userLoginCommand.setUserName("zy");
        //thrown.expect(WrongPasswordException.class);
        try {
            ret = identityCmdService.login(userLoginCommand);
        }catch (WrongPasswordException e){
            assertThat(e,
                    isA(WrongPasswordException.class));
        }

        assertFalse(ret);


        logger.info("testUserLogin test correct");
        userLoginCommand.setPassword("pwd");
        ret = identityCmdService.login(userLoginCommand);
        assertTrue(ret);

        logger.info("testUserLogin test finished ret:{}", ret);
    }
}
