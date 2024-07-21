package com.thinksync.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDetails {
  @Email(message = "Invalid email address")
  private String emailId;

  @NotNull(message = "Invalid password")
  @Size(min = 6, message = "Password should have at least 6 characters")
  private String password;

  @NotBlank(message = "Role cannot be blank")
  private String role;

  @NotBlank(message = "First Name cannot be blank")
  private String firstName;

  @NotBlank(message = "Last Name cannot be blank")
  private String lastName;

  @NotNull(message = "Age cannot be null")
  @Min(value = 18, message = "Age should not be less than 18")
  @Max(value = 100, message = "Age should not be greater than 100")
  private Integer age;

  @NotNull(message = "Salary cannot be null")
  @Min(value = 0, message = "Salary should not be less than 0")
  private Float salary;
}
