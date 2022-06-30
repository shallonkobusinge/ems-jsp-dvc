package com.ems.server.serviceImp;

import com.ems.server.dto.CreateOrUpdateEmployeeDTO;
import com.ems.server.enums.EDepartments;
import com.ems.server.models.Employee;
import com.ems.server.models.User;
import com.ems.server.repository.IEmployeeRepository;
import com.ems.server.services.IEmployeeService;
import com.ems.server.services.IUserService;
import com.ems.server.utils.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final IUserService userService;

    public EmployeeServiceImpl(IEmployeeRepository employeeRepository, IUserService userService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Employee save(CreateOrUpdateEmployeeDTO dto) {
        User existingUser = userService.findById(dto.getUserId());

        Employee employee = new Employee();
        employee.setUser(existingUser);
        employee.setDepartments(dto.getDepartments());
        return employeeRepository.save(employee);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id.toString() + " not found"));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Employee update(CreateOrUpdateEmployeeDTO dto, Long id) {
        Employee existingEmployee = findById(id);
        existingEmployee.setUser(userService.findById(dto.getUserId()));
        return employeeRepository.save(existingEmployee);

    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Employee remove(Long id) {
        Employee existingEmployee = findById(id);
        employeeRepository.delete(existingEmployee);
        return existingEmployee;
         }
}
