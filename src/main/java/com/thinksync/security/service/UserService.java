package com.thinksync.security.service;

import com.thinksync.security.dto.UserDetails;
import com.thinksync.security.dto.UserInfo;
import com.thinksync.security.entity.User;

public interface UserService {
   UserInfo createNewUser(UserDetails user);

   UserInfo getUserDetails(String emailId);
}
