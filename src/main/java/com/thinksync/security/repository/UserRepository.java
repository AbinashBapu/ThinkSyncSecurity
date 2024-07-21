package com.thinksync.security.repository;

import com.thinksync.security.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
  Optional<User> findByEmailId(String emailId);
}
