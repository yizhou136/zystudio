package com.zy.weibo.bootconfig.mvc;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author by zy.
 */
@Component
public class KryoHttpMessageConverter extends AbstractHttpMessageConverter<Object>{
    public static final MediaType KRYO = new MediaType("application", "x-kryo");

    private Kryo kryo;

    public KryoHttpMessageConverter(){
        super(KRYO);
        kryo = new Kryo();
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Input input = new Input(inputMessage.getBody());
        return kryo.readClassAndObject(input);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Output output = new Output(outputMessage.getBody());
        kryo.writeClassAndObject(output, o);
        output.flush();
    }
}