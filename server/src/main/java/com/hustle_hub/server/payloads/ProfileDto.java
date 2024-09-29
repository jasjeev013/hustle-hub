package com.hustle_hub.server.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Long   id;
    private String firstName;
    private String lastName;
    private String profileImageURL;
    private String bio;



}
