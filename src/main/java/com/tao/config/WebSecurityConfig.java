package com.tao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author TAO
 * @Date 2018/2/23 16:00
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/feedback.html").authenticated()
                .and()
                .formLogin().loginPage("/login")
                .failureUrl("/login?err").defaultSuccessUrl("/feedback.html").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .rememberMe().tokenValiditySeconds(60 * 60 * 24 * 30);//登录状态保持一周
    }

    @Autowired
    public void  configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("500w").password("luckyboy").roles("USER");
    }
}
