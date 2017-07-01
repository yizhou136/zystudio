package com.zy.userservice.application;


import com.zy.userservice.AbstractSpringBootTest;
import com.zy.userservice.application.command.UserLoginCommand;
import com.zy.userservice.application.exception.UserNotExistsException;
import com.zy.userservice.application.exception.WrongPasswordException;
import static org.junit.Assert.*;

import com.zy.userservice.domain.cmdmodel.identity.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.hamcrest.core.Is.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
/**
 * @author by zy.
 */
@RunWith(MockitoJUnitRunner.class)
public class IdentityCmdServiceTest{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IdentityCmdService identityCmdService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp(){

    }

    @Test
    public void testJava1(){
        List<String> list = mock(List.class);

        when(list.get(0)).thenReturn("hello world");
        doThrow(new RuntimeException("adsfasdf")).when(list).get(1);
        when(list.get(2)).thenThrow(RuntimeException.class);

        try {
            list.get(1);
        }catch (RuntimeException e){
            verify(list).get(1);
        }

        String result = list.get(0);
        verify(list, times(1)).get(0);
        assertEquals("hello world", result);

        Map<Integer,String> map = mock(Map.class);
        when(map.put(anyInt(),anyString())).thenReturn("hello");
        when(map.get(1)).thenReturn("world");
        map.put(1, "world");
        verify(map).put(eq(1), eq("world"));

        result = map.get(1);
        assertEquals("world", result);

        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getParameter("foo")).thenReturn("bao");

        result = httpServletRequest.getParameter("foo");
        assertEquals("bao", result);
    }

    /*@Test
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
    }*/
}
