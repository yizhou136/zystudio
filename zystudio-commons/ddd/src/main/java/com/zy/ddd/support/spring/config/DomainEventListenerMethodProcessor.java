package com.zy.ddd.support.spring.config;

import com.zy.ddd.domain.DefaultDomainEventPublisher;
import com.zy.ddd.support.spring.annotation.DomainMethodEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author by zy.
 */
public class DomainEventListenerMethodProcessor extends EventListenerMethodProcessor{
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected ConfigurableApplicationContext applicationContext;
    protected final Set<Class<?>> nonAnnotatedClasses =
            Collections.newSetFromMap(new ConcurrentHashMap<Class<?>, Boolean>(64));

    //private DomainEventPublisher domainEventPublisher;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Assert.isTrue(applicationContext instanceof ConfigurableApplicationContext,
                "ApplicationContext does not implement ConfigurableApplicationContext");
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
        super.setApplicationContext(applicationContext);
    }

    protected void processBean(final List<EventListenerFactory> factories, final String beanName, final Class<?> targetType) {
        /*if (domainEventPublisher == null){
            domainEventPublisher = applicationContext.getBean(DomainEventPublisher.class);
        }
        if (domainEventPublisher == null){
            logger.error("the domainEventPublisher == null, so cant register DomainMethodEventListener:",
                    beanName);
            return;
        }*/
        if (!this.nonAnnotatedClasses.contains(targetType)) {
            Map<Method, DomainMethodEventListener> annotatedMethods = null;
            try {
                annotatedMethods = MethodIntrospector.selectMethods(targetType,
                        new MethodIntrospector.MetadataLookup<DomainMethodEventListener>() {
                            @Override
                            public DomainMethodEventListener inspect(Method method) {
                                return AnnotatedElementUtils.findMergedAnnotation(method, DomainMethodEventListener.class);
                            }
                        });
            }
            catch (Throwable ex) {
                // An unresolvable type in a method signature, probably from a lazy bean - let's ignore it.
                logger.debug("Could not resolve methods for bean with name '" + beanName + "'", ex);
            }
            if (CollectionUtils.isEmpty(annotatedMethods)) {
                this.nonAnnotatedClasses.add(targetType);
                logger.trace("No @DomainMethodEventListener annotations found on bean class: " + targetType);
            }
            else {
                // Non-empty set of methods
                for (Method method : annotatedMethods.keySet()) {
                    Method methodToUse = AopUtils.selectInvocableMethod(
                            method, this.applicationContext.getType(beanName));

                    DomainListenerMethodAdapter domainListenerMethodAdapter =
                            new DomainListenerMethodAdapter(applicationContext, beanName, targetType, methodToUse);
                    DefaultDomainEventPublisher
                            .getInstance()
                            .registerEventListener(domainListenerMethodAdapter);
                }
                logger.debug(annotatedMethods.size() + " @DomainMethodEventListener methods processed on bean '" +
                            beanName + "': " + annotatedMethods);
            }
        }
    }
}
