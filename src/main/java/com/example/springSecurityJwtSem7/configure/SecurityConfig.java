package com.example.springSecurityJwtSem7.configure;

import com.example.springSecurityJwtSem7.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * конфигурационный класс для настройки безопасности приложения
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// Отключаем защиты CSRF, будем использовать JWT
                .authorizeRequests()
                .antMatchers("/login").permitAll() // доступ для всех к /login
                .anyRequest().authenticated() // остальные требуют аутентификации
                .and()
                //.addFilter(new JwtAuthenticationFilter())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }*/

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER");
    }*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailService userDetailService(PasswordEncoder encoder){
        List<UserDetails> userList = new ArrayList<>();
        userList.add(new User("admin", encoder.encode("secret"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        userList.add(new User("user",encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        return (UserDetailService) new InMemoryUserDetailsManager(userList);
    }
}
