package com.zy.weibo.test.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.zy.weibo.beans.User;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * @author by zy.
 */
public class KryoUserTest {

    @Test
    public void testEncoding(){
        User user = new User();
        user.setUid(Long.valueOf(1));
        user.setName("zy");
        user.setCity("bj");

        Kryo kryo = new Kryo();
        byte bytes[] = new byte[34];
        Output output = new Output(bytes, bytes.length);
        kryo.writeClassAndObject(output, user);
        System.out.println(bytes.length);


        Input input = new Input(bytes);
        User u = (User) kryo.readClassAndObject(input);
        System.out.println(u);
    }
}
