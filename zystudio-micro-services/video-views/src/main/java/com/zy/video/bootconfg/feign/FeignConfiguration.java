package com.zy.weibo.configure.feign;

import com.google.common.base.Optional;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.ExecutionType;
import com.netflix.hystrix.contrib.javanica.command.MetaHolder;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import com.netflix.hystrix.contrib.javanica.utils.AopUtils;
import feign.Feign;
import feign.Target;
import feign.hystrix.SetterFactory;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.List;

import static com.netflix.hystrix.contrib.javanica.utils.EnvUtils.isCompileWeaving;

/**
 * @author by zy.
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public SetterFactory setterFactory(){
        SetterFactory setterFactory = new SetterFactory(){
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                String groupKey = target.name();
                String commandKey = Feign.configKey(target.type(), method);
                MetaHolder metaHolder = createBuilder(target, method);
                HystrixCommandProperties.Setter setter = null;
                if (metaHolder != null) {
                    List<HystrixProperty> commandProperties = metaHolder.getCommandProperties();
                    setter = HystrixPropertiesManager.initializeCommandProperties(commandProperties);
                }else {
                    setter = HystrixCommandProperties.defaultSetter();
                }
                /*HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(false)
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE));*/
                return HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                        .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                        .andCommandPropertiesDefaults(setter);
            }
        };
        return setterFactory;
    }

    private static MetaHolder createBuilder(Target<?> target, Method method){
        com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand hystrixCommand =
                method.getAnnotation(com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand.class);
        if (hystrixCommand == null)
            return null;
        ExecutionType executionType = ExecutionType.getExecutionType(method.getReturnType());
        MetaHolder.Builder builder = MetaHolder.builder()
                .method(method);

        //setFallbackMethod(builder, obj.getClass(), method);
        builder = setDefaultProperties(builder, target.type());

        //MetaHolder.Builder builder = metaHolderBuilder(proxy, method, obj, args, joinPoint);
        /*if (isCompileWeaving()) {
            builder.ajcMethod(getAjcMethodFromTarget(joinPoint));
        }*/

        return builder.defaultCommandKey(method.getName())
                .hystrixCommand(hystrixCommand)
                .observableExecutionMode(hystrixCommand.observableExecutionMode())
                .executionType(executionType)
                .observable(ExecutionType.OBSERVABLE == executionType)
                .build();
    }

    private static MetaHolder.Builder setDefaultProperties(MetaHolder.Builder builder, Class<?> declaringClass) {
        Optional<DefaultProperties> defaultPropertiesOpt = AopUtils.getAnnotation(declaringClass, DefaultProperties.class);
        builder.defaultGroupKey(declaringClass.getSimpleName());
        if (defaultPropertiesOpt.isPresent()) {
            DefaultProperties defaultProperties = defaultPropertiesOpt.get();
            builder.defaultProperties(defaultProperties);
            if (StringUtils.isNotBlank(defaultProperties.groupKey())) {
                builder.defaultGroupKey(defaultProperties.groupKey());
            }
            if (StringUtils.isNotBlank(defaultProperties.threadPoolKey())) {
                builder.defaultThreadPoolKey(defaultProperties.threadPoolKey());
            }
        }
        return builder;
    }
}