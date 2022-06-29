package com.ems.server.dto;

import com.ems.server.enums.ERole;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
public class CreateOrUpdateUserDTO {
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Phone is required")
    private String phone;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ERole role;

    public CreateOrUpdateUserDTO(String firstName, String lastName, String email, String phone, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.username = username;
    }

}
