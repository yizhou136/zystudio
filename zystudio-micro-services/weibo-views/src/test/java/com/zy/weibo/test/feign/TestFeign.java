package com.zy.weibo.test.feign;

import feign.Feign;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/23.
 */

public class TestFeign {

    @Test
    public void testFeign(){
        WeiboService weiboService = Feign.builder()
                .encoder(new MyEncode())
                .decoder(new MyDecoder())
                .target(WeiboService.class, "http://bjhost:2223");

        int res = weiboService.add("3", "2");
        System.out.println("res:"+res);
    }
}
