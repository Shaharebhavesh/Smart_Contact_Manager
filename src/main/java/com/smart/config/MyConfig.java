package com.smart.config;

import org.hibernate.cache.internal.DisabledCaching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity

public class MyConfig {
	
	  @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
	        return daoAuthenticationProvider;
	    }

//	    @Bean
//	    
//	    public AuthenticationManager authenticationManagerBean() throws Exception {
//	        return new AuthenticationManagerBean();
//	    }
	    
  
	    @Bean
	    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests(authorizeRequests ->
	                authorizeRequests
	                    .requestMatchers("/admin/**").hasRole("ADMIN")
	                    .requestMatchers("/user/**").hasRole("USER")
	                   .requestMatchers("/**").permitAll()
	            )
	            
	            .formLogin()
	            .loginPage("/signin")
	            .loginProcessingUrl("/dologin")
	            .defaultSuccessUrl("/user/index")	
	            .and()
	            .csrf().disable();

	        return http.build();
	    }

//	    //
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeHttpRequests(authorizeRequests ->
//	                authorizeRequests
//	                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
//	                    .requestMatchers("/user/**").hasAnyAuthority("USER")
//	                    .requestMatchers("/**").permitAll()
//	            )
//	            
//	            .formLogin()
//	            .and()
//	           .csrf(csrf ->csrf.disable());
//
//	        return http.build();
//	    }

	/*    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http
	                .csrf()
	                .disable()
	                .httpBasic()
	                .and()
	                .authorizeHttpRequests()
	               //.requestMatchers("/user/**").hasRole("USER")
	                .requestMatchers("/user/**").hasAuthority("USER")
	                .anyRequest().authenticated()
	                .and()
	                .formLogin().permitAll()
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);;

	        return http.build();
	    }*/
	    
	    private static class AuthenticationManagerBean implements AuthenticationManager {
	        @Override
	      
	        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return authentication;
	            // Your authentication logic here
	        }
	    }
    }
    
    

