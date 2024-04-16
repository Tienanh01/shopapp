package com.project.shopapp.config;

import com.project.shopapp.Repository.RoleRepository;
import com.project.shopapp.Repository.UserRepository;
import com.project.shopapp.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // (1)
public class WebSecurityConfig  { // (1)
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository ;

    public static final String[] PUBLIC_PATHS = {"/api/auth/**",
            "/v3/api-docs.yaml",
            "/v3/**",
            "/swagger-ui/**",
            "/swagger-ui.html"};

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers(PUBLIC_PATHS).permitAll();
                    authorize.requestMatchers("**").permitAll();
//
////                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
////                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
////                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
////                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    // authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
//                    // authorize.requestMatchers(HttpMethod.GET, "/api1/**").permitAll();
//
//
//
//                    authorize
//                            .requestMatchers(PUBLIC_PATHS).permitAll()
//                            .requestMatchers("/swagger/**").permitAll().
//                            requestMatchers("/api/admin/**").hasRole("ADMIN").
//
////                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//        requestMatchers("/api/auth/login").permitAll()
//                            .anyRequest().authenticated();
                }) ;
//                .httpBasic(Customizer.withDefaults());
//
//        http.exceptionHandling( exception -> exception
//                .authenticationEntryPoint(authenticationEntryPoint));
//
//        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user1234")
//                .password("password5678")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin1234")
//                .password("password5678")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//@Bean
//public UserDetailsService userDetailsService() {
//
//        String phonenumber ="String";
//
//    com.project.shopapp.models.User user =  (userRepository.findByPhoneNumber(phonenumber)).get();
//
//    UserDetails userDetails = User.withDefaultPasswordEncoder()
//            .username(user.getPhoneNumber())
//            .password(user.getPassword())
//            .roles(user.getRole().getName())
//            .build();
//
//    return new InMemoryUserDetailsManager(userDetails);
//}


}
