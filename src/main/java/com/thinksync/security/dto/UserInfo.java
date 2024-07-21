package com.thinksync.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
  private String emailId;
  private String firstName;
  private String lastName;
  private Integer age;
  private Float salary;
}
