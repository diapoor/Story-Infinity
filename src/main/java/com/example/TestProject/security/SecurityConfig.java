package com.example.TestProject.security;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig  {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private  AuthenticationProvider authenticationProvider;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    //Specifies the sequence of security filters applied to incoming HTTP requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);//Disables CSRF protection. CSRF (Cross-Site Request Forgery) protection is being turned off here
        http.exceptionHandling((exception)-> exception.authenticationEntryPoint(customAuthenticationEntryPoint));
        http.authorizeHttpRequests(request-> //Defines authorization rules for different request matchers:
                        request.requestMatchers("/api/v1/auth/**").permitAll() // are permitted to all users.

                                .requestMatchers("/api/v1/author/**").hasAnyAuthority("ROLE_MODERATOR","ROLE_ADMIN")//Author accept mod,admin

                                .requestMatchers("/api/v1/genres/public/{genresId}/listStory").permitAll()
                                .requestMatchers("/api/v1/genres/list").permitAll()
                                .requestMatchers("/api/v1/genres/**").hasAnyAuthority("ROLE_MODERATOR","ROLE_ADMIN")//genre accept by mode,admin

                                .requestMatchers("/api/v1/employees/**").hasAnyAuthority("ROLE_ADMIN")//employee accept by admin

                                .requestMatchers("/api/v1/roles/**").hasAnyAuthority("ROLE_ADMIN")//role accept by admin

                                .requestMatchers("/api/v1/users/admin/**").hasAnyAuthority("ROLE_ADMIN") //users/admin accept by admin
                                .requestMatchers("/api/v1/users/update").hasAnyAuthority("ROLE_USER") //users/update accept by user
                                .requestMatchers("/api/v1/users/details").hasAnyAuthority("ROLE_USER") //users/details accept by user
                                .requestMatchers("/api/v1/users/getRole").hasAnyAuthority("ROLE_USER") //users/details accept by user

                                .requestMatchers("/api/v1/stories/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_EDITOR","ROLE_MODERATOR")//stories/admin accept by edit,mod,admin
                                .requestMatchers("/api/v1/stories/user/**").hasAnyAuthority("ROLE_USER")//stories/user accept by user
                                .requestMatchers("/api/v1/stories/public/**").permitAll()

                                .requestMatchers("/api/v1/chapter/public/**").permitAll()
                                .requestMatchers("/api/v1/chapter/user/**").hasAnyAuthority("ROLE_USER")
                                .requestMatchers("/api/v1/chapter/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_EDITOR","ROLE_MODERATOR")

                                .requestMatchers("/api/v1/favorite/**").hasAnyAuthority("ROLE_USER")

                                .requestMatchers("/api/v1/history/add").permitAll()
                                .requestMatchers("/api/v1/history/**").hasAnyAuthority("ROLE_USER")

                                .requestMatchers("/api/v1/comment/public/**").permitAll()
                                .requestMatchers("/api/v1/comment/user/**").hasAnyAuthority("ROLE_USER")
                                .requestMatchers("/api/v1/comment/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_MODERATOR")

                                .requestMatchers("/api/v1/rating/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_MODERATOR")
                                .requestMatchers("/api/v1/rating/user/**").hasAnyAuthority("ROLE_USER")
                                .requestMatchers("/api/v1/rating/public/**").permitAll()
                                .anyRequest().permitAll())
                .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
