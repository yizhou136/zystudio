package com.zy.registry.test.service.impl;

import com.zy.registry.test.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author by zy.
 */
public class UserServiceImpl implements UserService{
    @Override
    public boolean authUser() {
        return false;
    }
}
