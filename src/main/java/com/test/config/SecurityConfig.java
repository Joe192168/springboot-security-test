package com.test.config;

import com.test.handler.FailHandler;
import com.test.handler.MyAccessDenied;
import com.test.handler.SuccessHandler;
import com.test.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 会拦截注解了@PreAuthrize注解的配置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private DataSource dataSource;*/
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 授权-安全规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // authorizeRequests() 方法表示开启权限配置
        http.authorizeRequests()
                // 和PreAuthorize是相同的效果
                .antMatchers("/admin","/user").hasAnyRole("ADMIN")
                // 表示所有的请求都要认证后才能访问
                .anyRequest().authenticated()
                .and()
                // 开启表单登录配置
                .formLogin()
                //.usernameParameter("username")
                //.passwordParameter("password")
                // 配置登录页面地址
                .loginPage("/tologin")
                //指定自定义form表单请求的路径、默认：/login
                .loginProcessingUrl("/dologin")
                // 配置登录成功的跳转地址
                //.successForwardUrl("/toindex")//认证成功 forward 跳转路径 始终在认证成功之后跳转到指定请求
                .defaultSuccessUrl("/toindex")//认证成功 redirect 如果之前请求路径,会有优先跳转之前请求路径
                // 自定义认证成功处理程序
                //.successHandler(new SuccessHandler())
                // 配置登陆失败的跳转地址
                .failureUrl("/fail")
                // 自定义认证失败处理程序
                //.failureHandler(new FailHandler())
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                // 禁用CSRF防御功能功能，SpringSecurity 自带了 CSRF 防御机制，
                // 但是我们这里为了测试方便，先将CSRF防御机制关闭
                .csrf().disable();

        //自定义403权限不足的返回值
        http.exceptionHandling().accessDeniedHandler(new MyAccessDenied());
    }

    /**
     * 移除ROLE_前缀
     * @return
     */
    /*@Bean
    protected GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }*/

    /**
     * 认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //第一种：内存存储方式
        /*auth.inMemoryAuthentication().passwordEncoder(bCryptPasswordEncoder())
                .withUser("admin").password(bCryptPasswordEncoder().encode("123")).authorities("ADMIN")
                .and()
                .withUser("zhangsan").password(bCryptPasswordEncoder().encode("123")).authorities("USER");*/
        //第二种：数据库存储
        /*auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select username, password from users where username = ? and password =?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authority where username = ?");*/
        //第三种：LDAP存储
        /*LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> configurer =                     auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}");

        configurer.passwordCompare()
                .passwordEncoder(passwordEncoder())
                .passwordAttribute("passcode");
        configurer.contextSource().url("ldap://xxxxx.com:33389/dc=xxxxxx,dc=com");*/
        //第四种：自定义
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
