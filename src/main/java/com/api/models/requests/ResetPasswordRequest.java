package com.api.models.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResetPasswordRequest {

    private String confirmPassword;
    private String newPassword;
    private String token;
}
