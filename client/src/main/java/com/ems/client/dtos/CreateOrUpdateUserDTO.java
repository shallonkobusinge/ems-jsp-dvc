package com.ems.client.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateOrUpdateUserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String password;

    public CreateOrUpdateUserDTO(String firstName, String lastName, String email, String phone, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.username = username;
    }
}
