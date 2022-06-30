package com.ems.client.controllers;

import com.ems.client.dtos.CreateOrUpdateUserDTO;
import com.ems.client.dtos.LoginDTO;
import com.ems.client.dtos.User;
import com.ems.client.enums.ERole;
import com.ems.client.utils.JwtAuthenticationResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {
    @GetMapping
    public String mainEntry(){
        return "Register";
    }
    @GetMapping("login-page")
    public String login(){
        return "Login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model, HttpSession session){
        String username = request.getParameter("login");
        String password = request.getParameter("password");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/v1/users/login",new LoginDTO(username,password),String.class);

        String token = "Bearer "+responseEntity.getBody();
        request.getSession().setAttribute("token",token);
        HttpHeaders headers =  new HttpHeaders();
        headers.set("Authorization",token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return "redirect:/employees/add-page";
    }
//    @GetMapping("/my-profile")
//    public String getProfile(HttpServletRequest request) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//
//        String token = request.getSession().getAttribute("token").toString();
//        headers.setBearerAuth(token);
//
//        HttpEntity<String> entity = new HttpEntity<>("", headers);
//
//        ResponseEntity<String> userResponse = restTemplate.exchange("the Url Here", HttpMethod.GET, entity, String.class);
//
//        System.out.println(userResponse.getBody());
//
//        return "todos/all";
//    }
    @PostMapping("register")
    public String register(String firstName, String lastName, String email, String phone, String password, String username, ERole role){
        CreateOrUpdateUserDTO dto = new CreateOrUpdateUserDTO(firstName, lastName, email, phone, password, username,role);
        System.out.println("user name "+username+"  dto "+dto.getUsername()+" role "+dto.getRole());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/api/v1/users/add",dto,String.class);

        return "redirect:/login-page";
    }

    @PostMapping("/edit")
    public String updateUser(String firstName, String lastName, String email, String phone, String password, String username, Long id,ERole role, Model model, HttpServletRequest request){
        CreateOrUpdateUserDTO dto = new CreateOrUpdateUserDTO(firstName, lastName, email, phone, password, username,role);
        String URL = "http://localhost:8080/api/v1/users/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(URL,dto,request.getParameter("id"));
        return "redirect:/employees/all";
    }
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
