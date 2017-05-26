package com.zy.userservice.domain.cmdmodel.identity.events;

import com.zy.ddd.domain.DomainEvent;
import com.zy.userservice.domain.cmdmodel.identity.User;
import com.zy.userservice.domain.cmdmodel.identity.UserDetail;

/**
 * @author by zy.
 */
public class RegisteringUserDomainEvent extends DomainEvent{
    public String name;
    public String password;
    public String headface;
    public UserDetail userDetail;

    public RegisteringUserDomainEvent(Object source, String name, String password, String headface, UserDetail userDetail) {
        super(source);
        this.name = name;
        this.password = password;
        this.headface = headface;
        this.userDetail = userDetail;
    }

    public static RegisteringUserDomainEvent of(User user){
        return new RegisteringUserDomainEvent(user,user.getName(),
                user.getPassword(), user.getHeadface(),user.getUserDetail());
    }
}