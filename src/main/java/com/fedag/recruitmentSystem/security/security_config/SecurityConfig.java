package com.fedag.recruitmentSystem.security.security_config;

import com.fedag.recruitmentSystem.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable() //защита от csrf угрозы
               .authorizeRequests()     //указываем, кто куда имеет доступ
               .antMatchers("/").permitAll()//тут можно созд стартовый ендпоинт
               // для авторизации и туда пускать всех
               //Укажем что методы GET могут делать все, а остальные только admin
               .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(Role.ADMIN.name(),
                       Role.USER.name())
               .antMatchers(HttpMethod.POST,"/api/**").hasRole(Role.ADMIN.name())
               .antMatchers(HttpMethod.PUT,"/api/**").hasRole(Role.ADMIN.name())
               .antMatchers(HttpMethod.DELETE,"/api/**").hasRole(Role.ADMIN.name())
               .anyRequest() //каждый запрос
               .authenticated() // должен быть аутентифицирован
               .and()
               .httpBasic();
    }

    //Создал 2 пользователей inmemory
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles(Role.ADMIN.name())
                        .build(),
        User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles(Role.USER.name())
                .build()
        );
    }

    protected PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
