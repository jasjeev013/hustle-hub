package com.hustle_hub.server.repositories;

import com.hustle_hub.server.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
