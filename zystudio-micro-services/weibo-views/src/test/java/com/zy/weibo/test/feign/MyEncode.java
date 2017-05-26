package com.zy.weibo.test.feign;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/23.
 */
public class MyEncode implements Encoder{
    private static final Logger logger = LoggerFactory.getLogger(MyEncode.class);

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        logger.info("encode object:{} template:{}",
                object, template);
        if (object instanceof Map){
            StringBuilder sb = new StringBuilder();
            Map<String ,Object> map = (Map<String,Object>)object;
            map.forEach((k,v)->{
                    sb.append(k).append("=").append(v);
                sb.append("&");
            });
            String body = sb.toString();
            logger.info("encode body:{}", body);
            //template.body(body);
            //template.req
        }
    }
}
