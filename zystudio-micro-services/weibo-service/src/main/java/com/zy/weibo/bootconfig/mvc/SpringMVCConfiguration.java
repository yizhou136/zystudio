package com.zy.weibo.bootconfig.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import java.time.Instant;

/**
 * Created by zhougb on 2016/11/4.
 */
//@Configuration
//@EnableWebMvc
public class SpringMVCConfiguration extends WebMvcConfigurationSupport {

    @Override
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
        //InternalResourceView internalResourceView = new InternalResourceViewResolver();
        registry.enableContentNegotiation(mappingJackson2JsonView, mappingJackson2XmlView);
        registry.freeMarker();
        registry.jsp();
        //registry.jsp("/templates",".html");
        //registry.getViewResolvers().add();
    }

    /*@Bean(name = "zyUser")
    @Scope("request")
    public User zy(){
        User user = new User();
        user.setUname("zy"+ Instant.now().getEpochSecond());
        user.setAge((byte)11);
        return user;
    }*/
}
