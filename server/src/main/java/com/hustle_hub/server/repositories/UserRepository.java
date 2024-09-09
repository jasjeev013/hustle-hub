package com.hustle_hub.server.repositories;

import com.hustle_hub.server.models.Profile;
import com.hustle_hub.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByProfile(Profile profile);
}
