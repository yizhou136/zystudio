package com.zy.userservice.domain.readmodel.identity;

/**
 * @author by zy.
 */
public interface UserQuery {

    UserDto findById(Long uid);

}