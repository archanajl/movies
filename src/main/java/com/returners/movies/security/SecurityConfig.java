package com.returners.movies.security;

import com.returners.movies.filter.CustomAuthenticationFilter;
import com.returners.movies.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/login/**","/access/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers(POST,"/movies/**").hasAnyAuthority("MANAGER");
        http.authorizeRequests().antMatchers(POST,"/movies/search/**").hasAnyAuthority("VIEWER","MANAGER");
        http.authorizeRequests().antMatchers(DELETE,"/movies/**").hasAnyAuthority("MANAGER");
        http.authorizeRequests().antMatchers(POST,"/users/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/users/**").hasAnyAuthority("VIEWER","ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"/users/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/favourites/**").hasAnyAuthority("VIEWER");
        http.authorizeRequests().antMatchers(DELETE,"/favourites/**").hasAnyAuthority("VIEWER");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
