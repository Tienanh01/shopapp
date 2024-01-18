package com.project.shopapp.config;

import com.project.shopapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class SecurityConfig {
    // userDetail
    @Autowired
    private   UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService(){
//        return "unique filed user password";
        return phoneNumber ->
            userRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                    () -> new UsernameNotFoundException(" Can not find user with phone number")
            );
    }
}
