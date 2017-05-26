package com.zy.userservice.application;

import com.zy.userservice.application.command.RegisterUserCommand;
import com.zy.userservice.application.command.UserLoginCommand;
import com.zy.userservice.application.exception.UserNotExistsException;
import com.zy.userservice.application.exception.WrongPasswordException;
import com.zy.userservice.commons.MyPreconditions;
import com.zy.userservice.domain.cmdmodel.identity.User;
import com.zy.userservice.domain.cmdmodel.identity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by zy.
 */
@Service
public class IdentityCmdService {
    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(RegisterUserCommand command){
        User user = command.toUser();
        return user.registeUser();
    }

    public boolean login(UserLoginCommand loginCommand){
        User user = userRepository.findByName(loginCommand.getUserName());

        MyPreconditions.requireNonNull(user,
                ()->{return String.format("the user(%s) is not exists", loginCommand.getUserName());},
                UserNotExistsException.class);

        boolean res = user.authenticatingUserPassword(loginCommand.getPassword());
        MyPreconditions.checkArgument(res,
                ()->{return String.format("the input password(%s) is wrong", loginCommand.getPassword());},
                WrongPasswordException.class);

        return res;
    }
}