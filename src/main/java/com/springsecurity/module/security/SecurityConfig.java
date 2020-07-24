package com.springsecurity.module.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.springsecurity.module.security.Role.ADMIN;
import static com.springsecurity.module.security.Role.CUSTOMER;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/css/*", "/js/*").permitAll()

//                .antMatchers("/api/dishes/**").hasRole(CUSTOMER.name())
//                .antMatchers("/api/customers/**").hasRole(CUSTOMER.name())

//                .antMatchers("/api/orders/**").hasAnyAuthority(Permissions.ORDER_WRITE.getPermission())
//                .antMatchers("/api/orders/**").hasAnyRole(CUSTOMER.name(), ADMIN.name())


                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
//                .roles(CUSTOMER.name())
                .authorities(CUSTOMER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                user
        );
    }
}
