package com.movie.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                    .antMatchers("/","/index**","/subject/**","/search/**","/error**","/login**","/register**","/logout**","/api/**").permitAll() // 免认证目录
                    .antMatchers("/admin").hasRole("ADMIN")// ADMIN角色可以访问api目录
                    .anyRequest().authenticated()
                    .and().formLogin().loginPage("/adminlogin").permitAll()// 自定义登录页为/login
                    .and().logout().permitAll()
                    .and().headers().frameOptions().disable()
                    .and().csrf().disable();
        }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
    }
}
