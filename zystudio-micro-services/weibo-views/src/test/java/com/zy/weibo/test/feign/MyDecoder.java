package com.zy.weibo.test.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2017/4/23.
 */
public class MyDecoder extends StringDecoder{
    private static final Logger logger = LoggerFactory.getLogger(MyDecoder.class);

    @Override
    public Object decode(Response response, Type type) throws IOException {
        Response.Body body = response.body();
        logger.info("decode response:{}  type:{} body:{}"
                ,response, type, body);
        if (type == Integer.class){
            BufferedReader reader = new BufferedReader(body.asReader());
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }

            return  Integer.parseInt(stringBuilder.toString());
        }
        return super.decode(response, type);
    }
}
