package com.zy.weibo.test.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

/**
 * @author by zy.
 */
public class HystrixHelloWorldObservableCommand extends HystrixObservableCommand<String> {
    private static final Logger logger = LoggerFactory.getLogger(HystrixHelloWorldObservableCommand.class);
    private String name;

    public HystrixHelloWorldObservableCommand(String name){
        super(HystrixCommandGroupKey.Factory.asKey("HystrixHelloWorldGroup"));
        this.name = name;
    }

    @Override
    protected String getCacheKey() {
        return "oneCache";
    }

    @Override
    protected Observable<String> construct() {
        //if (true) throw new RuntimeException("construct error:"+name);
        logger.info("run {}",name);
        return Observable.from(new String[]{"a","b","c","d"});
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return Observable.just("resumeWithFallback "+name);
    }
}