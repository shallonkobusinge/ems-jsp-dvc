package com.ems.server.controllers;

import com.ems.server.dto.CreateOrUpdateEmployeeDTO;
import com.ems.server.services.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> add(@RequestBody CreateOrUpdateEmployeeDTO dto){
        System.out.println("Depart "+ dto.getDepartments());
        return ResponseEntity.ok(employeeService.save(dto));
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(employeeService.findAll());
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> update(@RequestBody CreateOrUpdateEmployeeDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(employeeService.update(dto,id));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.remove(id));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }
}
