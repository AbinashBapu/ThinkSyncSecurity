package com.thinksync.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User extends CreationUpdationOps{
  @Id
  @GeneratedValue
  private UUID pkUserId;

  @Column(nullable = false, unique = true)
  private String emailId;

  @Column(nullable = false)
  private String hash;

  @Column(nullable = false)
  private String authorities;


}
