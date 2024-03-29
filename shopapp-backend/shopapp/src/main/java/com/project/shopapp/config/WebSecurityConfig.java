package com.project.shopapp.config;

import com.project.shopapp.Filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private  JwtTokenFilter jwtTokenFilter ;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
          http
                .csrf(AbstractHttpConfigurer::disable)
                  .addFilterBefore(jwtTokenFilter , UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request ->{
                    request.requestMatchers("**").permitAll();
                });
        return http.build();
    }
}
