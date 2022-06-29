package com.ems.server.dto;

import com.ems.server.enums.EDepartments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateOrUpdateEmployeeDTO {
    private Long userId;
    private EDepartments departments;
}
