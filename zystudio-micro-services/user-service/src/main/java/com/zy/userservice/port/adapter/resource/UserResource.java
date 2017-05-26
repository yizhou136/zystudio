package com.zy.userservice.port.adapter.resource;

import com.zy.userservice.application.IdentityCmdService;
import com.zy.userservice.domain.readmodel.identity.UserDto;
import com.zy.userservice.domain.readmodel.identity.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author by zy.
 */
@Path("/user")
@Component
public class UserResource {
    @Autowired
    private IdentityCmdService identityApplicationService;

    @Autowired
    private UserQuery userQuery;

    @Path("{uid}")
    public UserDto getUser(@PathParam("uid") Long uid){
        return userQuery.findById(uid);
    }
}