package com.thinksync.security.controller;

import com.thinksync.security.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/associates")
public class AssociateController {

  @GetMapping({"","/"})
  public AppResponse getAllAssociates(){
    return AppResponse.builder().data("data").message("Fetched successfully").status(HttpStatus.OK).build();
  }

}
