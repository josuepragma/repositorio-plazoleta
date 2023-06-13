package com.pragma.smallsquare.restaurant.insfrastructure.security.configuration;

import com.pragma.smallsquare.restaurant.insfrastructure.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private static final String ROLE_ADMIN = "ADMINISTRADOR";
    private static final String ROLE_OWNER = "PROPIETARIO";
    private static final String ROLE_EMPLOYEE = "EMPLEADO";
    private static final String ROLE_CLIENT = "CLIENTE";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .antMatchers(
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.POST, "/small-square/restaurant/**").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/small-square/restaurant/**").hasAnyRole(ROLE_ADMIN, ROLE_OWNER)
                .antMatchers(HttpMethod.POST, "/small-square/dish/**").hasRole(ROLE_OWNER)
                .antMatchers(HttpMethod.GET, "/small-square/dish/**").hasAnyRole(ROLE_ADMIN, ROLE_OWNER)
                .antMatchers(HttpMethod.PUT, "/small-square/dish/**").hasRole(ROLE_OWNER)
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
