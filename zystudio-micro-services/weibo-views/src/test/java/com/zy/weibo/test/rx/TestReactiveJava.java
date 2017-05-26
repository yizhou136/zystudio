package com.zy.weibo.test.rx;

import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.subjects.ReplaySubject;

/**
 * @author by zy.
 */
public class TestReactiveJava {

    private Observer<Integer> integerObserver = new Observer<Integer>() {
        @Override
        public void onCompleted() {
            System.out.println("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError e"+e);
        }

        @Override
        public void onNext(Integer integer) {
            System.out.println("onNext integer:"+integer);
        }
    };

    private Observer<String> stringObservable = new Observer<String>(){
        @Override
        public void onCompleted() {
            System.out.println("stringObservable onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError e:"+e);
        }

        @Override
        public void onNext(String s) {
            System.out.println("onNext s:"+s);
        }
    };

    @Test
    public void testDemo(){
        Observable<Integer> observable = Observable.create(new Observable.
                OnSubscribe<Integer>(){
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onCompleted();
            }
        });



        observable.subscribe(integerObserver);

        Observable
                .from(new String[]{"a","b","c"})
                .map((s)->{return "app_"+s;})
                .subscribe(System.out::println);

        Observable<String> o = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                System.out.println("create an Observable");
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        System.out.println("emit str");
                        subscriber.onNext("a");
                        //subscriber.onError(new RuntimeException("dddd"));
                        //subscriber.onCompleted();
                    }
                });
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                System.out.println("flatMap s:"+s);
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        System.out.println("flatmap emit s:");
                        subscriber.onNext("b");
                    }
                });
            }
        });

        System.out.println("test defer");
        o
                .doOnUnsubscribe(()->{System.out.println("unsubscribe");})
                .doOnCompleted(()->{System.out.println("onCompleted");})
                .doOnError((e)->{System.out.println("onError e"+e);})
                .subscribe(stringObservable);


        System.out.println("test lift");
        Observable.Operator operator1 = new Observable.Operator<Integer, Integer>() {
            @Override
            public Subscriber<? super Integer> call(Subscriber<? super Integer> subscriber) {
                if (subscriber instanceof MySubscriber)
                    System.out.println("operator subscriber1:"+((MySubscriber)subscriber).name);
                else
                    System.out.println("operator subscriber1:"+subscriber);
                return new MySubscriber<Integer>("MySubscriber1");
            }
        };
        Observable.Operator operator2 = new Observable.Operator<Integer, Integer>() {
            @Override
            public Subscriber<? super Integer> call(Subscriber<? super Integer> subscriber) {
                if (subscriber instanceof MySubscriber)
                    System.out.println("operator subscriber2:"+((MySubscriber)subscriber).name);
                else
                    System.out.println("operator subscriber2:"+subscriber);
                return new MySubscriber<Integer>("MySubscriber2");
            }
        };
        Observable.from(new Integer[]{1,2,3,4,5,6,7}).map((i)->{return i++;}).take(5)
        .lift(operator1).lift(operator2).lift(operator2).subscribe();
    }

    private static class MySubscriber<R> extends Subscriber<R>{
        private String name;
        public MySubscriber(String name){
            this.name = name;
        }
        @Override
        public void onCompleted() {
            System.out.println("onCompleted");
        }
        @Override
        public void onError(Throwable e) {
            System.out.println("onError e"+e);
        }
        @Override
        public void onNext(R r) {
            System.out.println("onNext integer:"+r+" name:"+name);
        }
    }

    @Test
    public void testReplaySubject(){
        ReplaySubject<String> subject = ReplaySubject.create();
        /*subject.onNext("1");
        subject.onNext("2");
        subject.onNext("3");*/
        System.out.println("subscriber:"+subject);
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("xxx subscriber:"+subscriber);
                subscriber.onNext("a");
                subscriber.onNext("b");
                System.out.println("end subscriber:"+subscriber);
            }
        }).subscribe(subject);

        System.out.println("end  ");
        //subject.subscribe(System.out::print);
        System.out.println();
    }
}
