package com.ems.client.dtos;

import com.ems.client.enums.EDepartments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
public class Employee {

    private Long id;

    private User user;
    private EDepartments departments;

    public Employee(User user, EDepartments departments) {
        this.user = user;
        this.departments = departments;
    }
}
