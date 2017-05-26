package com.zy.weibo.test.hystrix;

import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author by zy.
 */
public class HystrixTest {

    @Test
    public void testHystrix() throws InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            HystrixHelloWorld hystrixHelloWorld1 = new HystrixHelloWorld("zy1");
            HystrixHelloWorld hystrixHelloWorld2 = new HystrixHelloWorld("zy2");

            System.out.println("start create hystrixHelloWorld1.observe");
            Observable<String> observable1 = hystrixHelloWorld1.observe();
            //Future<String> future = hystrixHelloWorld1.queue();
            //Observable<String> observable2 = hystrixHelloWorld1.toObservable();
            //System.out.println("end create hystrixHelloWorld1.observe and start subscribe");
            //observable1.toBlocking().toFuture();
            //System.out.println("end subscribe");
            //Observable<String> observable2 = hystrixHelloWorld2.observe();
            //Observable<String> observable2 = hystrixHelloWorld2.toObservable();

            //observable2.subscribe();
            //System.out.println("observable1.subscribe");
            //Subscription subscription1 = observable1.subscribe();


            observable1.subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    System.out.println("res "+s);
                }
            });
            System.out.println("start wait");
            Thread.sleep(300);

            /*Future<String> future = hystrixHelloWorld2.queue();
            String str = future.get();
            System.out.println("str:"+str+" "+hystrixHelloWorld2.isResponseFromCache());*/

            System.out.println("test observablecommand");
            if (true) {
                HystrixObservableCommand hystrixObservableCommand3 = new HystrixHelloWorldObservableCommand("zy3");
                Observable<String> observable3 = hystrixObservableCommand3.toObservable();
                HystrixObservableCommand hystrixObservableCommand4 = new HystrixHelloWorldObservableCommand("zy4");
                Observable<String> observable4 = hystrixObservableCommand4.toObservable();

                System.out.println("create toObservable3 and toObservable4");
                observable3.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("hystrixObservableCommand3 result:" + s);
                    }
                });
                observable4.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("hystrixObservableCommand4 result:" + s);
                    }
                });
            }
        /*} catch (InterruptedException e) {
            e.printStackTrace();
        /*} catch (ExecutionException e) {
            e.printStackTrace();*/
        } finally {
            context.shutdown();
        }
    }
}

