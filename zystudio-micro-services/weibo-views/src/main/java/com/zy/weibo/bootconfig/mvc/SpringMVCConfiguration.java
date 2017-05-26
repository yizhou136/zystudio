package com.zy.weibo.bootconfig.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.time.Instant;
import java.util.List;

/**
 * Created by zhougb on 2016/11/4.
 */
//@Configuration
//@EnableWebMvc
public class SpringMVCConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    /*@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);
        configurer.defaultContentType(MediaType.APPLICATION_XHTML_XML);
        configurer.favorParameter(false);
        configurer.favorPathExtension(true);
        configurer.ignoreUnknownPathExtensions(true);
        configurer.ignoreAcceptHeader(true);
    }

    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        MappingJackson2XmlView mappingJackson2XmlView = new MappingJackson2XmlView();
        registry.enableContentNegotiation(mappingJackson2JsonView, mappingJackson2XmlView);
        registry.freeMarker();
        registry.jsp();
    }

    @Bean(name = "zyUser")
    @Scope("request")
    public User zy(){
        User user = new User();
        user.setUname("zy"+ Instant.now().getEpochSecond());
        user.setAge((byte)11);
        return user;
    }*/
}
