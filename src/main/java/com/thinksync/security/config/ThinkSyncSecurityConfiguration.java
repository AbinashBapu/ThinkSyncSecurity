package com.thinksync.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

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



}
