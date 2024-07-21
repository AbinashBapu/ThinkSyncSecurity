package com.thinksync.security.controller;

import com.thinksync.security.dto.UserDetails;
import com.thinksync.security.dto.UserInfo;
import com.thinksync.security.entity.User;
import com.thinksync.security.response.AppResponse;
import com.thinksync.security.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/public/registration")
public class UserRegistrationController {

  private UserService userService;

  @PostMapping
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserDetails userdetails){
    UserInfo user = userService.createNewUser(userdetails);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/api/associates/{emailId}")
        .buildAndExpand(user.getEmailId())
        .toUri();
    return ResponseEntity.created(location).build();
  }


}
