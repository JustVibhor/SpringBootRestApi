package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {

    //Filter Chain
    //by default spring security authenticates all requests
    //basic authentication for all the request
    //disabling csrf
    //stateless r•••••est api
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(
                        // all http request should be authenticated
                        auth -> auth
                                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .anyRequest().authenticated()
                )

                // enabling basic authentication
                // pop up instead of webpage for authentication
                .httpBasic(Customizer.withDefaults())

                // configuring stateless session
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .csrf().disable();

        return http.build();
    }
}
