package com.zy.userservice.domain.cmdmodel.identity;

import org.junit.Test;
import org.springframework.util.Assert;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author by zy.
 */
public class TelephoneTest {

    @Test
    public void testUri(){
        String uriStr = "http://north_bj_yw.regcen.01:1101/eureka/";
        uriStr = "http://01.bj.regcen.com:1101/eureka/";
        try {
            URI uri = new URI(uriStr);
            Assert.notNull(uri.getHost(), "must not be null");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
