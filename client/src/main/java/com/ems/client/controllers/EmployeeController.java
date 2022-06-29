package com.ems.client.controllers;

import com.ems.client.dtos.CreateOrUpdateEmployeeDTO;
import com.ems.client.dtos.Employee;
import com.ems.client.dtos.User;
import com.ems.client.enums.EDepartments;
import com.ems.client.utils.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @GetMapping("/all")
    public String getAll(Model model){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee[]> employees = restTemplate.getForEntity("http://localhost:6000/employees", Employee[].class);
        model.addAttribute("employees",employees.getBody());
        return "employees/ViewEmployees";
    }
    @GetMapping("/add-page")
    public String save(Model model){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> users = restTemplate.getForEntity("http://localhost:6000/user", User[].class);
        model.addAttribute("users",users.getBody());
        return "employees/RegisterEmployees";
    }

    @PostMapping("/create")
    public String create(Long userId, EDepartments departments, Model model, HttpServletRequest request){
        System.out.println("User id "+userId+" department "+departments);
        RestTemplate restTemplate = new RestTemplate();

        model.addAttribute("user",new User());
        String department = request.getParameter("departments");
        model.addAttribute(department, Arrays.asList(EDepartments.values()));
        String id = request.getParameter("userId");
        model.addAttribute("userId",id);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:6000/employees/add",new CreateOrUpdateEmployeeDTO(userId,departments),String.class);
        System.out.println("Output : "+ responseEntity.getBody());
        return "redirect:/employees/all";
    }

    @GetMapping("/delete")
    public String delete(Long id,HttpServletRequest request){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:6000/employees/{id}";
        restTemplate.delete(URL,request.getParameter("id"));
        return "redirect:/employees/all";
    }

    @GetMapping("one")
    public String getSingle(HttpServletRequest request,Model model){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:6000/employees/{id}";
        Employee employee = restTemplate.getForObject(URL,Employee.class,request.getParameter("id"));
        model.addAttribute("employee",employee);
        return "EditForm";
    }
}
