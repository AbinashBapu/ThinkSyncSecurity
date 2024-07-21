package com.thinksync.security.repository;

import com.thinksync.security.entity.User;
import com.thinksync.security.entity.UserProfile;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, UUID> {
  UserProfile findByUser(User user);
}
