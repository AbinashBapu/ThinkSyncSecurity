package com.thinksync.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name="user_profiles")
public class UserProfile extends CreationUpdationOps{

  @Id
  @GeneratedValue
  private UUID pkUserProfileId;

  private String firstname;

  private String lastname;

  private int age;

  private float salary;

  @OneToOne
  @JoinColumn(name = "fk_user_id", referencedColumnName = "pkUserId")
  private User user;

}
