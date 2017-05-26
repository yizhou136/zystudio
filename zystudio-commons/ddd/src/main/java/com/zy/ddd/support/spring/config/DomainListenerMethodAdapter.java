package com.zy.ddd.support.spring.config;

import com.zy.ddd.domain.DomainEvent;
import com.zy.ddd.domain.DomainEventListener;
import com.zy.ddd.domain.EventResult;
import com.zy.ddd.support.spring.annotation.DomainMethodEventListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.Order;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author by zy.
 */
public class DomainListenerMethodAdapter implements DomainEventListener<DomainEvent>{
    private final String beanName;
    private final Method method;
    private final int order;

    private final Class<?> targetClass;

    private final Method bridgedMethod;

    private final List<ResolvableType> declaredEventTypes;

    private final AnnotatedElementKey methodKey;

    private ApplicationContext applicationContext;

    public DomainListenerMethodAdapter(ApplicationContext applicationContext,
                                       String beanName, Class<?> targetClass, Method method) {
        this.applicationContext = applicationContext;
        this.beanName = beanName;
        this.method = method;
        this.targetClass = targetClass;
        this.bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);

        DomainMethodEventListener ann = AnnotatedElementUtils.findMergedAnnotation(
                method, DomainMethodEventListener.class);
        this.declaredEventTypes = resolveDeclaredEventTypes(method, ann);

        this.methodKey = new AnnotatedElementKey(method, targetClass);

        Order order = method.getDeclaredAnnotation(Order.class);
        if (order != null)
            this.order = order.value();
        else
            this.order = 0;
    }

    private List<ResolvableType> resolveDeclaredEventTypes(Method method, DomainMethodEventListener ann) {
        int count = method.getParameterTypes().length;
        if (count > 2) {
            throw new IllegalStateException(
                    "Maximum two parameter is allowed for event listener method: " + method);
        }
        if (ann != null && ann.classes().length > 0) {
            List<ResolvableType> types = new ArrayList<ResolvableType>(ann.classes().length);
            for (Class<?> eventType : ann.classes()) {
                types.add(ResolvableType.forClass(eventType));
            }
            return types;
        }
        else {
            if (count == 0) {
                throw new IllegalStateException(
                        "Event parameter is mandatory for event listener method: " + method);
            }
            return Collections.singletonList(ResolvableType.forMethodParameter(method, 0));
        }
    }

    @Override
    public <R> EventResult<R> handleEvent(DomainEvent domainEvent, EventResult eventResult) {
        return (EventResult<R>) doInvoke(resolveArguments(domainEvent, eventResult));
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public Class[] receiveActualType() {
        Class actualTypes[] = new Class[declaredEventTypes.size()];
        for (int i=0; i<declaredEventTypes.size(); i++){
            ResolvableType r = declaredEventTypes.get(i);
            actualTypes[i] = r.getRawClass();
        }
        return actualTypes;
    }

    protected Object[] resolveArguments(DomainEvent event, EventResult eventResult) {
        if (this.method.getParameterTypes().length == 0) {
            return new Object[0];
        }

        return new Object[] {event, eventResult};
    }

    protected Object doInvoke(Object... args) {
        Object bean = getTargetBean();
        ReflectionUtils.makeAccessible(this.bridgedMethod);
        try {
            return this.bridgedMethod.invoke(bean, args);
        }
        catch (IllegalArgumentException ex) {
            assertTargetBean(this.bridgedMethod, bean, args);
            throw new IllegalStateException(getInvocationErrorMessage(bean, ex.getMessage(), args), ex);
        }
        catch (IllegalAccessException ex) {
            throw new IllegalStateException(getInvocationErrorMessage(bean, ex.getMessage(), args), ex);
        }
        catch (InvocationTargetException ex) {
            // Throw underlying exception
            Throwable targetException = ex.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw (RuntimeException) targetException;
            }
            else {
                String msg = getInvocationErrorMessage(bean, "Failed to invoke event listener method", args);
                throw new UndeclaredThrowableException(targetException, msg);
            }
        }
    }

    protected Object getTargetBean() {
        Assert.notNull(this.applicationContext, "ApplicationContext must no be null");
        return this.applicationContext.getBean(this.beanName);
    }

    private void assertTargetBean(Method method, Object targetBean, Object[] args) {
        Class<?> methodDeclaringClass = method.getDeclaringClass();
        Class<?> targetBeanClass = targetBean.getClass();
        if (!methodDeclaringClass.isAssignableFrom(targetBeanClass)) {
            String msg = "The event listener method class '" + methodDeclaringClass.getName() +
                    "' is not an instance of the actual bean class '" +
                    targetBeanClass.getName() + "'. If the bean requires proxying " +
                    "(e.g. due to @Transactional), please use class-based proxying.";
            throw new IllegalStateException(getInvocationErrorMessage(targetBean, msg, args));
        }
    }

    private String getInvocationErrorMessage(Object bean, String message, Object[] resolvedArgs) {
        StringBuilder sb = new StringBuilder(getDetailedErrorMessage(bean, message));
        sb.append("Resolved arguments: \n");
        for (int i = 0; i < resolvedArgs.length; i++) {
            sb.append("[").append(i).append("] ");
            if (resolvedArgs[i] == null) {
                sb.append("[null] \n");
            }
            else {
                sb.append("[type=").append(resolvedArgs[i].getClass().getName()).append("] ");
                sb.append("[value=").append(resolvedArgs[i]).append("]\n");
            }
        }
        return sb.toString();
    }

    protected String getDetailedErrorMessage(Object bean, String message) {
        StringBuilder sb = new StringBuilder(message).append("\n");
        sb.append("HandlerMethod details: \n");
        sb.append("Bean [").append(bean.getClass().getName()).append("]\n");
        sb.append("Method [").append(this.bridgedMethod.toGenericString()).append("]\n");
        return sb.toString();
    }
}
