package com.zy.weibo.test.feign;

import feign.Param;
import feign.RequestLine;

/**
 * Created by Administrator on 2017/4/23.
 */
public interface WeiboService {

    @RequestLine("GET /add?a={a}&b={b}")
    public Integer add(@Param("a") String a, @Param("b") String b);
}
