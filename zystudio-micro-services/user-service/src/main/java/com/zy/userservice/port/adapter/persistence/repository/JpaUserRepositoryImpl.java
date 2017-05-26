package com.zy.userservice.port.adapter.persistence.repository;

import com.zy.ddd.domain.EventResult;
import com.zy.ddd.support.spring.annotation.DomainMethodEventListener;
import com.zy.userservice.domain.cmdmodel.identity.User;
import com.zy.userservice.domain.cmdmodel.identity.events.RegisteringUserDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author by zy.
 */
public class JpaUserRepositoryImpl {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EntityManager em;


    @DomainMethodEventListener(classes = {RegisteringUserDomainEvent.class})
    @Order(0)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public EventResult<Boolean> handle(RegisteringUserDomainEvent registeringUserDomainEvent,
                                       EventResult prevResult) {
        logger.info("handle registeringUserDomainEvent:{} prevResult:{}",
                registeringUserDomainEvent, prevResult);

        User user = (User)registeringUserDomainEvent.getSource();
        em.persist(user);
        logger.info("handle registeringUserDomainEvent end:{}", user.getUid());
        return EventResult.finishedResult(true);
    }

    /*
        /*@Query("from User u where u.id = :id")
        //@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    //Person findById(@Param("id")long id);
    User load(@Param("id")Long id);*/
}