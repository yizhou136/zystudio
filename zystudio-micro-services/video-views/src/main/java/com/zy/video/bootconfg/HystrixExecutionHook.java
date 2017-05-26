package com.zy.video.bootconfg;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author by zy.
 */
public class HystrixExecutionHook extends HystrixCommandExecutionHook{
    protected Logger logger = LoggerFactory.getLogger(HystrixExecutionHook.class);

    @Override
    public <T> void onStart(HystrixInvokable<T> commandInstance) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        logger.info("onStart commandInstance:{} requestAttributes:{}",
                commandInstance, requestAttributes);
        super.onStart(commandInstance);
    }
}
