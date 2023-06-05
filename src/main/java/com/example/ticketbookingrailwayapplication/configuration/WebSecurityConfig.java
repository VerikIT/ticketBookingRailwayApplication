package com.example.ticketbookingrailwayapplication.configuration;

import com.example.ticketbookingrailwayapplication.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/registration").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/hello")
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//
//        return authenticationConfiguration.getAuthenticationManager();
//    }


//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
//    }


}