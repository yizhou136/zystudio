package com.zy.userservice.port.adapter.persistence.repository;

import com.zy.ddd.support.spring.jpa.JpaBaseRepository;
import com.zy.userservice.domain.cmdmodel.identity.User;
import com.zy.userservice.domain.cmdmodel.identity.UserRepository;

/**
 * @author by zy.
 */
public interface JpaUserRepository extends JpaBaseRepository<User,Long>, UserRepository{
}
