package com.zy.ddd;

import com.zy.ddd.domain.DomainWithSpringTest;
import com.zy.ddd.domain.DomainWithoutSpringTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author by zy.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DomainWithoutSpringTest.class, DomainWithSpringTest.class})
public class DDDTestSuite {
}
