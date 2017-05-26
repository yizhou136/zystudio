package com.zy.registry.test.spring.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author by zy.
 */
@Component
public class MySmartLifeCycle implements SmartLifecycle{
    private static final Logger logger = LoggerFactory.getLogger(MySmartLifeCycle.class);
    private boolean isRunning;

    /*@Autowired
    private MyLifeCycle myLifeCycle;*/

    @Override
    public boolean isAutoStartup() {
        logger.info("isAutoStartup");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        logger.info("stop {}",callback);
    }

    @Override
    public void start() {
        isRunning = true;
        logger.info("start");
        //myLifeCycle.start();
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

    @Override
    public int getPhase() {
        logger.info("getPhase");
        return 0;
    }
}