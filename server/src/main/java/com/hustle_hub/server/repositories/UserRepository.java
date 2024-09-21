package com.hustle_hub.server.repositories;

import com.hustle_hub.server.models.Profile;
import com.hustle_hub.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByProfile(Profile profile);
    Optional<User> findByEmail(String email);
}
