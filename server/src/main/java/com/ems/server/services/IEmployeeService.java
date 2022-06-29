package com.ems.server.services;

import com.ems.server.dto.CreateOrUpdateEmployeeDTO;
import com.ems.server.models.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee save(CreateOrUpdateEmployeeDTO dto);
    List<Employee> findAll();
    Employee findById(Long id);
    Employee update(CreateOrUpdateEmployeeDTO dto, Long id);
    Employee remove(Long id);

}
