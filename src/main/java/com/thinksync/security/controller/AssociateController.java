package com.thinksync.security.controller;

import com.thinksync.security.dto.UserInfo;
import com.thinksync.security.response.AppResponse;
import com.thinksync.security.service.UserService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/associates")
public class AssociateController {

  private UserService userService;


  @GetMapping({"","/"})
  public ResponseEntity<AppResponse> getAllAssociates(){
    try {
      AppResponse response = AppResponse.builder().data("data").message("Fetched successfully")
          .status(HttpStatus.OK).build();
      return ResponseEntity.ok(response);
    }catch (Exception ex){
      AppResponse errorResponse = AppResponse.builder().data(null).message(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      return ResponseEntity.ok(errorResponse);
    }
  }

  @GetMapping("/{emailId}")
  public ResponseEntity<AppResponse> getAssociates(@PathVariable("emailId")String emailId){
    try {
      UserInfo userDetails = userService.getUserDetails(emailId);
      AppResponse response = AppResponse.builder().data(userDetails).message("Fetched successfully")
          .status(HttpStatus.OK).build();
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }catch (Exception ex){
      AppResponse errorResponse = AppResponse.builder().data(null).message(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      return ResponseEntity.ok(errorResponse);
    }
  }

}
