package com.zy.ddd.domain;

import java.util.Optional;

/**
 * @author by zy.
 */
public class EventResult<R> {
    /*public static enum EventState{
        EXCEPTIONED,
        FINISHED,
    }
    public EventState*/
    private boolean needContinue;

    private Optional<R> result;


    public EventResult(boolean needContinue, Optional<R> result){
        this.needContinue = needContinue;
        this.result = result;
    }

    public static <R> EventResult<R> finishedResult(R r){
        return new EventResult(false, Optional.of(r));
    }

    public static <R> EventResult<R> continueResult(R r){
        return new EventResult(true, Optional.of(r));
    }

    public boolean isNeedContinue() {
        return needContinue;
    }

    public void setNeedContinue(boolean needContinue) {
        this.needContinue = needContinue;
    }

    public Optional<R> getResult() {
        return result;
    }

    public void setResult(Optional<R> result) {
        this.result = result;
    }
}