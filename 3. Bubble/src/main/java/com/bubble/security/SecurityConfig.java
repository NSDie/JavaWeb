package com.bubble.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * 安全配置文件，主要是重写默认的认证方式和访问目录权限
 *
 * @author
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Web Form表单登录
     *
     * @author
     */

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/api/**") // 免认证目录
                    .permitAll().antMatchers("/swagger-ui.html**").hasRole("ADMIN")// ADMIN角色可以访问api目录
                    .anyRequest().authenticated().and().formLogin().loginPage("/login")// 自定义登录页为/login
                    .permitAll().and().logout().permitAll().and().csrf().disable();
        }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("1admin+2user").roles("ADMIN");
    }
}
