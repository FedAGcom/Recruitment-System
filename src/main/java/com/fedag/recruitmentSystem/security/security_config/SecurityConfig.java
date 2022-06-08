package com.fedag.recruitmentSystem.security.security_config;

import com.fedag.recruitmentSystem.enums.Permission;
import com.fedag.recruitmentSystem.enums.Role;
import com.fedag.recruitmentSystem.security.JwtConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AndRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //над SecurityConfig для включения возможности исп @PreAuthorize
//@PreAuthorize("hasAuthority('developers:read')") //над методами для указания прав доступа
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    //JWT
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/auth/login").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/**").hasAuthority(Permission.DEVELOPERS_READ.getPermission())
//                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }


    //С помощью Authority
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
////                .antMatchers(HttpMethod.GET,"/api/**").hasAuthority(Permission.DEVELOPERS_READ.getPermission())
////                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
////                .antMatchers(HttpMethod.PUT,"/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
////                .antMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(Permission.DEVELOPERS_WRITE.getPermission())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
////                .loginPage("api/auth").permitAll() // есди будем делать страницу под регистрацию
////                .defaultSuccessUrl("/")
//                     .and()
//                .logout()
////                .logoutRequestMatcher(new AndRequestMatcher("/auth/logout", "POST"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID");
////                .logoutSuccessUrl("/auth/login");
//    }

    //С помощью Authority
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .authorities(Role.ADMIN.getAuthorities())
//                        .build(),
//        User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .authorities(Role.USER.getAuthorities())
//                .build()
//        );
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @SuppressWarnings("deprecation")
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

//    @Bean
//    protected PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }



//    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }


    //С помощью Ролей
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//       http
//               .csrf().disable() //защита от csrf угрозы
//               .authorizeRequests()     //указываем, кто куда имеет доступ
//               .antMatchers("/").permitAll()//тут можно созд стартовый ендпоинт
//               // для авторизации и туда пускать всех
//               //Укажем что методы GET могут делать все, а остальные только admin
//               .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(Role.ADMIN.name(),
//                       Role.USER.name())
//               .antMatchers(HttpMethod.POST,"/api/**").hasRole(Role.ADMIN.name())
//               .antMatchers(HttpMethod.PUT,"/api/**").hasRole(Role.ADMIN.name())
//               .antMatchers(HttpMethod.DELETE,"/api/**").hasRole(Role.ADMIN.name())
//               .anyRequest() //каждый запрос
//               .authenticated() // должен быть аутентифицирован
//               .and()
//               .httpBasic();
//    }


    //Создал 2 пользователей inmemory
    //C помощью Ролей
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .roles(Role.ADMIN.name())
//                        .build(),
//        User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles(Role.USER.name())
//                .build()
//        );
//    }


}
