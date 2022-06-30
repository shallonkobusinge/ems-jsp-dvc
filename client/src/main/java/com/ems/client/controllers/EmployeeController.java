package com.ems.client.controllers;

import com.ems.client.dtos.CreateOrUpdateEmployeeDTO;
import com.ems.client.dtos.CreateOrUpdateUserDTO;
import com.ems.client.dtos.Employee;
import com.ems.client.dtos.User;
import com.ems.client.enums.EDepartments;
import com.ems.client.utils.APIResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @GetMapping("/all")
    public String getAll(Model model,HttpServletRequest request){
        String token = request.getSession().getAttribute("token").toString();
        HttpHeaders headers = getHeaders();
        headers.set("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Employee[]> employees = restTemplate.getForEntity("http://localhost:8080/employees", Employee[].class);
        ResponseEntity<Employee[]> employees = restTemplate.exchange("http://localhost:8080/api/v1/employees", HttpMethod.GET, entity, Employee[].class);

        model.addAttribute("employees",employees.getBody());
        return "employees/ViewEmployees";
    }
    @GetMapping("/add-page")
    public String save(HttpServletRequest request,Model model){
        RestTemplate restTemplate = new RestTemplate();
        String token = request.getSession().getAttribute("token").toString();
        HttpHeaders headers = getHeaders();
        headers.set("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<User[]> users = restTemplate.exchange("http://localhost:8080/api/v1/users", HttpMethod.GET,entity,User[].class);
        model.addAttribute("users",users.getBody());
        return "employees/RegisterEmployees";

    }

    @PostMapping("/create")
    public String create(Long userId, EDepartments departments, Model model, HttpServletRequest request){

        RestTemplate restTemplate = new RestTemplate();

        model.addAttribute("user",new User());
        String department = request.getParameter("departments");
        model.addAttribute(department, Arrays.asList(EDepartments.values()));
        String id = request.getParameter("userId");
        model.addAttribute("userId",id);


        String token = request.getSession().getAttribute("token").toString();
        HttpHeaders headers = getHeaders();
        headers.set("Authorization",token);

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("departments",departments);
        HttpEntity<Map<String,Object>> entity = new HttpEntity<>(map,headers);
        System.out.println("Mapppppppppppp "+entity.getBody());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/v1/employees/add",entity,String.class);

//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:6000/employees/add",new CreateOrUpdateEmployeeDTO(userId,departments),String.class);
        System.out.println("Output : "+ responseEntity.getBody());
        return "redirect:/employees/all";
    }

    @GetMapping("/delete")
    public String delete(Long id,HttpServletRequest request){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/employees/{id}";
        restTemplate.delete(URL,request.getParameter("id"));
        return "redirect:/employees/all";
    }

    @GetMapping("one")
    public String getSingle(HttpServletRequest request,Model model){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/employees/{id}";
        Employee employee = restTemplate.getForObject(URL,Employee.class,request.getParameter("id"));
        model.addAttribute("employee",employee);
        return "EditForm";
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
