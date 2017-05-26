package com.zy.userservice;

import com.zy.userservice.application.IdentityCmdServiceTest;
import com.zy.userservice.port.adapter.persistence.repository.JpaUserRepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author by zy.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({JpaUserRepositoryTest.class, IdentityCmdServiceTest.class})
public class UserServiceTestSuite {
}
