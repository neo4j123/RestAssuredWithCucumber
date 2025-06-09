package com.api.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;

    public UserProfileResponse() {
        // Default constructor for deserialization
    }


}
