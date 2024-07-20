package com.thinksync.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class ThinkSyncSecurityConfiguration {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity)throws Exception{
    httpSecurity.authorizeHttpRequests((request->request.requestMatchers("/public/**").permitAll()
        .requestMatchers("/api/**").authenticated()));
    httpSecurity.formLogin(AbstractHttpConfigurer::disable);
    httpSecurity.httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
  }

  /**
   * If you want to use string as password then use {noop} prefix to password
   * Default password encoder is bcrypt
   * Create 2 User: user1- test/test@123 and User2 - admin/4JpfY5sy630V admin is using bcrypt password encoder
   * 4JpfY5sy630V has hashed using bcrypt
   */
  @Bean
  UserDetailsService userDetailsService(){
    UserDetails user = User.withUsername("test").password("{noop}test@123").authorities("read").build();       // Uses NOOP password encoder
    UserDetails user2 = User.withUsername("admin").password("{bcrypt}$2a$12$Z5CfNvYDkKJGPS89E7FmROnOOU6G/47E2i00ZLoDD5dLSnma/VDdK").authorities("read_write").build(); // uses bcrypt password encoder

    return new InMemoryUserDetailsManager(user,user2);
  }

  // This is optional
  @Bean
  PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  // Check the password is compromised or not
  @Bean
  CompromisedPasswordChecker compromisedPasswordChecker(){
    return new HaveIBeenPwnedRestApiPasswordChecker();
  }

}
