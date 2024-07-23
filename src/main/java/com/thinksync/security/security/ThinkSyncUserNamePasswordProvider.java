package com.thinksync.security.security;

import com.thinksync.security.dto.UserInfo;
import com.thinksync.security.service.UserService;
import com.thinksync.security.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ThinkSyncUserNamePasswordProvider implements AuthenticationProvider {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserService userService;
  private PasswordEncoder passwordEncoder;


  /**
   * @param authentication the authentication request object.
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String emailId = authentication.getName();
    String pwd = authentication.getCredentials().toString();
    UserInfo userDetails = userService.getUserDetails(emailId);

    List<GrantedAuthority> authorityList = new ArrayList<>();

    String[] authorities = userDetails.getRole().split(";");
    for (String authority : authorities) {
      authorityList.add(new SimpleGrantedAuthority(authority));
    }

    if(userDetails.getAge()<18){
      logger.trace("User is minor, we stopped to access!");
      throw new BadCredentialsException("Invalid emailId or password!");
    }


    if (passwordEncoder.matches(pwd, userDetails.getHash())) {
      return new UsernamePasswordAuthenticationToken(emailId, pwd, authorityList);
    }
    throw new BadCredentialsException("Invalid emailId or password!");
  }

  /**
   * @param authentication
   * @return
   */
  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
