package com.hustle_hub.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;
    private String profileImageURL;
    private String bio;

    @OneToOne(mappedBy = "profile")
    private User user;


}
