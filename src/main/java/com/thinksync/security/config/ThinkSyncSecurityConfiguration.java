package com.thinksync.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class ThinkSyncSecurityConfiguration {

  /**
   * We are allowing all URI which started with public. We have disabled here csrf otherwise it will
   * not allow to register a new user.
   * <p>
   * CSRF (Cross-Site Request Forgery) is a security vulnerability where unauthorized commands are
   * transmitted from a user that the web application trusts. CSRF attacks exploit the trust that a
   * site has in the user's browser, allowing an attacker to perform actions on behalf of a user
   * without their consent.
   * <p>
   * In the context of a Spring Security application, CSRF protection is generally enabled by
   * default to prevent such attacks. However, there are scenarios where you might want or need to
   * disable CSRF protection:
   * <p>
   * When to Disable CSRF Protection APIs Used by Non-Browser Clients:
   * <p>
   * If your application is a RESTful API or provides services to non-browser clients (such as
   * mobile apps, third-party services, etc.), you might need to disable CSRF protection. CSRF
   * protection is primarily designed for web browsers where cookies are used for authentication.
   * Non-browser clients often use different authentication mechanisms, such as OAuth tokens or API
   * keys, which do not rely on cookies. Token-Based Authentication:
   * <p>
   * If you are using stateless authentication mechanisms like JWT (JSON Web Tokens) or other
   * token-based systems where the token is sent in headers (and not stored in cookies), CSRF
   * protection is not needed. This is because CSRF attacks primarily exploit cookies, and
   * token-based systems are less susceptible to such attacks. Single-Page Applications (SPAs):
   * <p>
   * For SPAs that use tokens (such as JWT) for authentication rather than cookies, disabling CSRF
   * might be appropriate. SPAs typically manage authentication state on the client side and send
   * tokens in HTTP headers, which are not vulnerable to CSRF in the same way as cookies.
   * Development and Testing:
   * <p>
   * In development or testing environments, disabling CSRF might simplify testing and development
   * by reducing the complexity of handling CSRF tokens. However, this should be used cautiously and
   * never in production environments unless absolutely necessary. How CSRF Protection Works CSRF
   * protection typically works by including a CSRF token in each request, which is validated on the
   * server side. The token ensures that the request is coming from a trusted source:
   * <p>
   * CSRF Token Generation:
   * <p>
   * The server generates a CSRF token and includes it in the web page (usually as a hidden field in
   * forms or as a header in AJAX requests). Token Validation:
   * <p>
   * When the client makes a request, it includes the CSRF token. The server checks the token to
   * ensure it matches the expected value.
   *
   * @param httpSecurity
   * @return
   * @throws Exception
   */


  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(csrfConfig -> csrfConfig.ignoringRequestMatchers("/public/**"))
        .authorizeHttpRequests((request -> request
            .requestMatchers("/public/**").permitAll()
            .anyRequest().authenticated()));
    httpSecurity.formLogin(AbstractHttpConfigurer::disable);
    httpSecurity.httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
  }

  // This is optional
  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  // Check the password is compromised or not
  @Bean
  CompromisedPasswordChecker compromisedPasswordChecker() {
    return new HaveIBeenPwnedRestApiPasswordChecker();
  }

}
