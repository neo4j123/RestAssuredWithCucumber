package com.api.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {


    private String token;
    private String type;
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    public LoginResponse(){

    }
}
