package com.zy.userservice.port.adapter.resource;

import com.zy.userservice.application.IdentityCmdService;
import com.zy.userservice.application.command.RegisterUserCommand;
import com.zy.userservice.domain.readmodel.identity.UserDto;
import com.zy.userservice.domain.readmodel.identity.UserQuery;
import com.zy.userservice.port.adapter.resource.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author by zy.
 */
@Path("/user")
@Component
public class UserResource {
    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);
    @Autowired
    private IdentityCmdService identityApplicationService;

    @Autowired
    private UserQuery userQuery;

    @Path("{uid}")
    public UserDto getUser(@PathParam("uid") Long uid){
        return userQuery.findById(uid);
    }


    @POST
    public UserVo regUser(UserVo userVo){
        RegisterUserCommand registerUserCommand = userVo.toRegisterUserCommand();
        UserDto userDto = identityApplicationService.registerUser(registerUserCommand);

        logger.info("regUser  userVo:{} userDto:{}", userVo, userDto);

        return UserVo.fromUserDto(userDto);
    }
}