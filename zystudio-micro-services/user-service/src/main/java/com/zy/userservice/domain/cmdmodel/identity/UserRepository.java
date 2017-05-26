package com.zy.userservice.domain.cmdmodel.identity;


import com.zy.ddd.domain.BaseRepository;

/**
 * @author by zy.
 */
public interface UserRepository extends BaseRepository<User,Long>{

    User findByName(String userName);
}