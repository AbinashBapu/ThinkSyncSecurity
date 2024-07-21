package com.thinksync.security.entity;

import jakarta.persistence.Column;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
public class CreationUpdationOps {
  @CreationTimestamp
  @Column(updatable = false)
  private ZonedDateTime createdOn;

  @UpdateTimestamp
  private ZonedDateTime updatedOn;

  private UUID updatedBy;
}
