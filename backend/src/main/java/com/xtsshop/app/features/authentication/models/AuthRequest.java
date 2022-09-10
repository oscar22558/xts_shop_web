package com.xtsshop.app.features.authentication.models;

import com.xtsshop.app.features.authentication.exceptions.AuthenticationRequestInvalidCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class AuthRequest {
    @NotEmpty(message = AuthenticationRequestInvalidCode.USERNAME_EMPTY)
    private String username;
    @NotEmpty(message = AuthenticationRequestInvalidCode.PASSWORD_EMPTY)
    private String password;
}
