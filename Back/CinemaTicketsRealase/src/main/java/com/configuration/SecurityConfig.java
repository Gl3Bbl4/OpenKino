package com.configuration;

import com.filter.EncodingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureSecurity(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/save","/**/*", "/*").permitAll()
                .anyRequest().permitAll()
        //.and().formLogin().defaultSuccessUrl("/", false)
        ;

    }
}