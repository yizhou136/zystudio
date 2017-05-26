package com.zy.weibo.bootconfig;

import com.netflix.hystrix.HystrixCachedObservable;
import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableHolder;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by zy.
 */
public class HystrixExecutionHook extends HystrixCommandExecutionHook{
    protected static final Logger logger = LoggerFactory.getLogger(HystrixExecutionHook.class);

    private static final HystrixRequestVariableHolder<HttpServletRequest> requestVariableForCache = new HystrixRequestVariableHolder<HttpServletRequest>(new HystrixRequestVariableLifecycle<HttpServletRequest>() {
        @Override
        public HttpServletRequest initialValue() {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
            HttpServletRequest httpServletRequest = requestAttributes.getRequest();
            logger.info("initialValue commandInstance: httpServletRequest:{}  param a:{}",
                     httpServletRequest, httpServletRequest.getQueryString());

            return httpServletRequest;
        }
        @Override
        public void shutdown(HttpServletRequest value) {
            // nothing to shutdown
            logger.info("shutdown commandInstance httpServletRequest:{}", value);
        }

    });
    private HystrixRequestContext hystrixRequestContext = null;

    public HystrixExecutionHook(){
    }

    @Override
    public <T> void onStart(HystrixInvokable<T> commandInstance) {
        hystrixRequestContext = HystrixRequestContext.initializeContext();
        HttpServletRequest httpServletRequest =
                requestVariableForCache.get(HystrixPlugins.getInstance().getConcurrencyStrategy());
        logger.info("onStart2 commandInstance:{} httpServletRequest:{}  param a:{}",
                commandInstance, httpServletRequest, httpServletRequest.getQueryString());
        super.onStart(commandInstance);
    }

    @Override
    public <T> void onThreadStart(HystrixInvokable<T> commandInstance) {
        HttpServletRequest httpServletRequest =
            requestVariableForCache.get(HystrixPlugins.getInstance().getConcurrencyStrategy());

        //RequestContextHolder.setRequestAttributes();
        logger.info("onThreadStart commandInstance:{} httpServletRequest:{}  param a:{}",
                commandInstance, httpServletRequest, httpServletRequest.getQueryString());

        super.onThreadStart(commandInstance);
    }

    /*@Override
    public <T> Exception onExecutionError(HystrixInvokable<T> commandInstance, Exception e) {
        hystrixRequestContext.shutdown();
        logger.info("onExecutionError commandInstance:{} requestAttributes:{}",
                commandInstance);
        return super.onExecutionError(commandInstance, e);
    }*/

    @Override
    public <T> void onThreadComplete(HystrixInvokable<T> commandInstance) {
        hystrixRequestContext.shutdown();
        logger.info("onThreadComplete commandInstance:{} requestAttributes:{}",
                commandInstance);
        super.onThreadComplete(commandInstance);
    }

    /*@Override
    public <T> T onEmit(HystrixInvokable<T> commandInstance, T value) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        logger.info("onEmit commandInstance:{} requestAttributes:{}",
                commandInstance, requestAttributes);
        return super.onEmit(commandInstance, value);
    }

    @Override
    public <T> void onExecutionStart(HystrixInvokable<T> commandInstance) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        logger.info("onExecutionStart commandInstance:{} requestAttributes:{}",
                commandInstance, requestAttributes);
        super.onExecutionStart(commandInstance);
    }*/
}
