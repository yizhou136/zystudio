package com.zy.userservice.port.adapter.resource;

import com.zy.ddd.domain.EventResult;
import com.zy.ddd.support.spring.annotation.DomainMethodEventListener;
import com.zy.userservice.domain.cmdmodel.identity.events.RegisteringUserDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author by zy.
 */
@Component
public class MailResource {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RestTemplate restTemplate;

    @DomainMethodEventListener(classes = {RegisteringUserDomainEvent.class})
    @Order(2)
    public EventResult postMail(RegisteringUserDomainEvent registeringUserDomainEvent,
                                EventResult prevResult){
        logger.info("postMail  registeringUserDomainEvent:{} prevResult:{}",
                registeringUserDomainEvent, prevResult);
        String url = "http://test.api.cuctv.com/statuses/user_timeline.json?uid=8803431&cuid=8913974&page=1&count=25&width=640&height=480&attachtype=0&access_token=399f7a5f8208416fadcd7fc6bb42d47b&api_key=1944417904174ed6aca19b44018e3327";
        String content = restTemplate.getForEntity(url,String.class).getBody();
        logger.info("postMail  registeringUserDomainEvent result:{}", content);
        return EventResult.continueResult(true);
    }
}