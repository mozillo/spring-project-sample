package C3Boot.code.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security 简单使用
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers( "/","/login").permitAll().anyRequest().authenticated()
                .and()
                //设置登录url
                .formLogin().loginPage("/login").successForwardUrl("/pages/chat.html").permitAll()
                .and()
                //登录失败
                .logout().permitAll()
                .and()
                //关闭csrf
                .csrf().disable();
    }

    /**
     * 设置2个用户
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("eddy").password("eddy").roles("USERS")
                .and()
                .withUser("andong").password("andong").roles("USERS");
    }

    /**
     * 不拦截的资源
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**",  "/assets/**", "/pages/**");
    }

}
