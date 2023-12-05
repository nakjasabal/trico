package com.edu.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	public MyAuthFailureHandler myAuthFailureHandler;

	/* 
	차후 여기에 있는 모든 주석을 다 풀면 됨.  
	*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) 
    		throws Exception {
        http.csrf((csrf) -> csrf.disable())
            .cors((cors) -> cors.disable())
            .authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/**").permitAll()
//                .requestMatchers("/admin34/login.do").permitAll()
                .requestMatchers("/css/**","/js/**","/images/**","/sbadmin/**").permitAll()                
                .requestMatchers("/xxxxxxxxxxxxxx/**").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/admin34/**").hasRole("ADMIN")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
            );        
        http.formLogin((formLogin) -> formLogin
//        		.loginPage("/admin34/login.do") 		// default : /login
//    	        .loginProcessingUrl("/admin34/loginAction.do")
//    	        .defaultSuccessUrl("/admin34/index.do")
//    	        .failureHandler(myAuthFailureHandler)
//    	        .usernameParameter("admin_id")		// default : username
//    	        .passwordParameter("admin_pass") 	// default : password
        		.permitAll());
        http.logout((logout) -> logout
//        		.logoutUrl("/admin34/logout.do") 		// default : /logout
//    	        .logoutSuccessUrl("/")
        		.permitAll());
//        http.exceptionHandling((expHandling) -> expHandling
//        		.accessDeniedPage("/admin34/login.do"));
        
        return http.build();
    }
    
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
				.username("nakja")
				.password(passwordEncoder().encode("1234"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("1234"))
				.roles("USER", "ADMIN")
				.build();

		// 메모리에 사용자 정보를 담는다.
		return new InMemoryUserDetailsManager(user, admin);
	}
	
    // passwordEncoder()
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

