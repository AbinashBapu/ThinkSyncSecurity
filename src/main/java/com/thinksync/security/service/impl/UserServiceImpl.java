package com.thinksync.security.service.impl;

import com.thinksync.security.dto.UserDetails;
import com.thinksync.security.dto.UserInfo;
import com.thinksync.security.entity.User;
import com.thinksync.security.entity.UserProfile;
import com.thinksync.security.repository.UserProfileRepository;
import com.thinksync.security.repository.UserRepository;
import com.thinksync.security.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private UserRepository userRepository;
  private UserProfileRepository userProfileRepository;
  private PasswordEncoder passwordEncoder;

  /**
   * @param userDetails
   */
  @Override
  public UserInfo createNewUser(UserDetails userDetails) {
    User user = new User();
    user.setEmailId(userDetails.getEmailId());
    user.setHash(passwordEncoder.encode(userDetails.getPassword()));
    user.setAuthorities(userDetails.getRole());
    User newUser = userRepository.save(user);
    logger.trace("User Created. UserId- {} :: emailId- {}",user.getPkUserId(),user.getEmailId());

    UserProfile userProfile = new UserProfile();
    userProfile.setFirstname(userDetails.getFirstName());
    userProfile.setLastname(userDetails.getLastName());
    userProfile.setAge(userDetails.getAge());
    userProfile.setSalary(userDetails.getSalary());
    userProfile.setUser(newUser);

    userProfileRepository.save(userProfile);
    logger.trace("User profile created! userProfileId- {}",userProfile.getPkUserProfileId());

    return UserInfo.builder().emailId(user.getEmailId()).firstName(userProfile.getFirstname())
            .lastName(userProfile.getLastname()).age(userProfile.getAge()).salary(userProfile.getSalary()).build();

  }

  /**
   * @param emailId 
   * @return
   */
  @Override
  public UserInfo getUserDetails(String emailId) {
    User userEntity = userRepository.findByEmailId(emailId).orElseThrow(()->new UsernameNotFoundException("user not found"));
    UserProfile userProfile = userProfileRepository.findByUser(userEntity);

    return UserInfo.builder().firstName(userProfile.getFirstname()).lastName(userProfile.getLastname())
        .emailId(userEntity.getEmailId()).salary(userProfile.getSalary()).age(userProfile.getAge())
        .hash(userProfile.getUser().getHash()).role(userProfile.getUser().getAuthorities()).build();
  }

  /**
   * @param username the username identifying the user whose data is required.
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public org.springframework.security.core.userdetails.UserDetails loadUserByUsername (
      String username) throws UsernameNotFoundException {
    User userEntity = userRepository.findByEmailId(username)
        .orElseThrow(()->new UsernameNotFoundException("Invalid EmailId or Password"));

    String[] authorities = userEntity.getAuthorities().split(",");
    List<GrantedAuthority> dbAuths = new ArrayList<>();
    for (String authority : authorities) {
      dbAuths.add(new SimpleGrantedAuthority(authority));
    }

    return new org.springframework.security.core.userdetails.User
        (userEntity.getEmailId(),userEntity.getHash(),true,true,
            true,true,dbAuths);
  }
}
