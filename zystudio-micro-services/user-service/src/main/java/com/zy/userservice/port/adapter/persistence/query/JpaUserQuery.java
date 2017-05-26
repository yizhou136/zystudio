package com.zy.userservice.port.adapter.persistence.query;

import com.zy.userservice.domain.cmdmodel.identity.User;
import com.zy.userservice.domain.readmodel.identity.UserDto;
import com.zy.userservice.domain.readmodel.identity.UserQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author by zy.
 */
public interface JpaUserQuery extends JpaRepository<User, Long>, UserQuery {

    @Query("select new com.zy.userservice.domain.readmodel.identity.UserDto(u) from User u where u.id = :id")
    UserDto findById(Long uid);
}
