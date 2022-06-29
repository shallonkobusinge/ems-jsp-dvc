package com.ems.server.models;

import com.ems.server.dto.CreateOrUpdateUserDTO;
import com.ems.server.dto.LoginDTO;
import com.ems.server.enums.ERole;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(schema = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    @Email
    @Column(unique = true)
    private String email;

//    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    public User(CreateOrUpdateUserDTO dto){
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.username = dto.getUsername();
        this.phone = dto.getPhone();
        this.role = dto.getRole();

    }


}
