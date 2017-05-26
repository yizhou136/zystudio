package com.zy.userservice.domain.cmdmodel.identity.events;

import com.zy.ddd.domain.DomainEvent;
import com.zy.userservice.domain.cmdmodel.identity.User;

/**
 * @author by zy.
 */
public class AuthenticatingUserPassword extends DomainEvent{
    private String password;
    private String userName;

    public AuthenticatingUserPassword(Object source, String userName,String password) {
        super(source);
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static AuthenticatingUserPassword of(User user){
        return new AuthenticatingUserPassword(user, user.getName(), user.getPassword());
    }
}