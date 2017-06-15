package com.zy.weibo.bootconfig.sleuth;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;

/**
 * @author by zy.
 */

public class AlwaysSampler implements Sampler{

    @Override
    public boolean isSampled(Span span) {
        return true;
    }
}
