package com.zy.registry.test.spring.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 * @author by zy.
 */
@Component
public class MyLifeCycle implements Lifecycle{
    private static final Logger logger = LoggerFactory.getLogger(MyLifeCycle.class);

    private boolean isRunning;

    @Override
    public void start() {
        isRunning = true;
        logger.info("start");
    }

    @Override
    public void stop() {
        isRunning = false;
        logger.info("stop");
    }

    @Override
    public boolean isRunning() {
        logger.info("isRunning {}", isRunning);
        return isRunning;
    }
}