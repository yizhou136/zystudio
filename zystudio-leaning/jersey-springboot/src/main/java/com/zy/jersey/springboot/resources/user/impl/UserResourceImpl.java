package com.zy.jersey.springboot.resources.user.impl;

import com.zy.jersey.springboot.resources.user.User;
import com.zy.jersey.springboot.resources.user.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author by zy.
 */
@Component
public class UserResourceImpl implements UserResource{
    //protected static final MapperObject
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String userName(String un) {
        logger.info("userName xxxxxxxxxxxxxxxx");
        return "{age:3}";
    }

    @Override
    public String getPicture(String make, PathSegment car, String year, @Context UriInfo info ) {
        return car.getMatrixParameters().getFirst("color");
    }


    public User getCustomer(int id) {
        final User customer = new User(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        User user = new User(id);
        user.setName("zy");
        return user;
        /*return new StreamingOutput() {
            public void write(OutputStream outputStream) throws IOException,
                    WebApplicationException {
                //outputCustomer(outputStream, customer);
                outputStream.write("{age:32}".getBytes());
            }
        };*/
    }
}
