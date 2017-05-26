package com.zy.registry.test.spring;

import com.zy.registry.test.AbstractSpringBootTest;
import com.zy.registry.test.spring.autowired.Filter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author by zy.
 */

public class SpringTest extends AbstractSpringBootTest{
    @Autowired
    private Map<String, Filter> mapFilter;

    @Test
    public void testRunit(){
        logger.info("mapFilter:{}", mapFilter);
    }
}