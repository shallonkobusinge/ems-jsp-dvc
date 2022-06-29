package com.ems.server.controllers;

import com.ems.server.dto.CreateOrUpdateEmployeeDTO;
import com.ems.server.services.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CreateOrUpdateEmployeeDTO dto){
        System.out.println("Depart "+ dto.getDepartments());
        return ResponseEntity.ok(employeeService.save(dto));
    }
    @GetMapping
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(employeeService.findAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CreateOrUpdateEmployeeDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(employeeService.update(dto,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.remove(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }
}
