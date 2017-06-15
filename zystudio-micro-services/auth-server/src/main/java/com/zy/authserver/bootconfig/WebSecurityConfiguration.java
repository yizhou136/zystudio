package com.zy.authserver.bootconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


/**
 * Created by zhougb on 2016/8/16.
 */
@Configuration
//@EnableWebSecurity
@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/person/*").permitAll()
                .antMatchers("/pc/*").permitAll()
                .antMatchers("/amqp/*").permitAll()
                //.antMatchers("/*.service").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/chat")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager);
        auth.inMemoryAuthentication()
                .withUser("wyf").password("wyf").roles("ACTUATOR")
                .and()
                .withUser("zgb").password("zgb").roles("ACTUATOR");
        /*auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select u.name as username , u.name as password , true from user u where  u.name = ?")
        .authoritiesByUsernameQuery("select u.name as username , 'USER' as authority from user u where  u.name = ?");*/
        //auth.userDetailsService(securityUserService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/resources/static/**");
                //.antMatchers("/*.service")
                //.antMatchers("/jaxws/*");
    }

    /*@Bean
    SecurityUserService securityUserService(){
        return new SecurityUserService();
    }*/
}