package com.hnp.usercore.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCoreDTO {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 10, max = 10)
    private String nationalCode;

    @Email(message = "email should be valid")
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Set<Long> roles;

    private Set<RoleCoreDTO> rolesCore;
}
