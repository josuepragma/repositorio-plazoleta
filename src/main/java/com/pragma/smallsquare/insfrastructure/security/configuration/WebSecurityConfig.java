package com.pragma.smallsquare.insfrastructure.security.configuration;

import com.pragma.smallsquare.insfrastructure.security.filter.JwtAuthenticationFilter;
import com.pragma.smallsquare.insfrastructure.util.RoleEnum;
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .antMatchers(
                        "/small-square/josue",
//                        "/small-square/dish/restaurant/{idRestaurant}/category/{idCategory}/list**",
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
                .antMatchers(HttpMethod.POST, "/small-square/employee/").hasRole(RoleEnum.OWNER.getName())
                .antMatchers(HttpMethod.POST, "/small-square/restaurant/**").hasRole(RoleEnum.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/small-square/restaurant/").hasAnyRole(RoleEnum.ADMIN.getName(), RoleEnum.OWNER.getName())
                .antMatchers(HttpMethod.POST, "/small-square/dish/").hasRole(RoleEnum.OWNER.getName())
                .antMatchers(HttpMethod.PUT, "/small-square/dish/**").hasRole(RoleEnum.OWNER.getName())
                .antMatchers(HttpMethod.GET, "/small-square/restaurant/list**").hasRole(RoleEnum.CUSTOMER.getName())
                .antMatchers(HttpMethod.GET, "/small-square/dish/restaurant/{idRestaurant}/category/{idCategory}/list**").hasAnyRole(RoleEnum.ADMIN.getName(), RoleEnum.OWNER.getName(), RoleEnum.CUSTOMER.getName())
                .antMatchers(HttpMethod.GET, "/small-square/order/").hasRole(RoleEnum.CUSTOMER.getName())
                .antMatchers(HttpMethod.GET, "/small-square/order/status/{statusName}/filter**").hasRole(RoleEnum.EMPLOYEE.getName())
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
