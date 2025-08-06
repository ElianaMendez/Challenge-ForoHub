package com.challenge.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationDTO(
        @NotBlank String login,
        @NotBlank String password
) {

}
