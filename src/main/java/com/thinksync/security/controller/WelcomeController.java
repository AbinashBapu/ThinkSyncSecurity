package com.thinksync.security.controller;

import com.thinksync.security.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/welcome")
public class WelcomeController {

  @GetMapping
  public AppResponse welcome(){
    return  new AppResponse(null,"Hello world!", HttpStatus.OK);
  }

}
