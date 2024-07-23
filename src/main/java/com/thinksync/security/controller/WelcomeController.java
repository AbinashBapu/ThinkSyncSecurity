package com.thinksync.security.controller;

import com.thinksync.security.response.AppResponse;
import com.thinksync.security.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/welcome")
public class WelcomeController {
  private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

  @GetMapping
  public ResponseEntity<AppResponse> welcome(){

    logger.error("ERROR Loaded!");
    logger.warn("WARN Loaded!");
    logger.info("INFO Loaded!");
    logger.debug("DEBUG Loaded!");
    logger.trace("TRACE Loaded!");

    AppResponse response = new AppResponse(null,"Hello world!", HttpStatus.OK);
    return ResponseEntity.ok(response);
  }

}
