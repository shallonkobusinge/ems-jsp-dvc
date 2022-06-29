package com.ems.client.controllers;

import com.ems.client.dtos.CreateOrUpdateUserDTO;
import com.ems.client.dtos.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("login")
    public String login(){
        return "Login";
    }
    @PostMapping("register")
    public String register(String firstName, String lastName, String email, String phone, String password, String username){
        CreateOrUpdateUserDTO dto = new CreateOrUpdateUserDTO(firstName, lastName, email, phone, password, username);
        System.out.println("user name "+username+"  dto "+dto.getUsername());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:6000/user/add",dto,String.class);
        return "redirect:/employees/add-page";
    }

    @PostMapping("/edit")
    public String updateUser(String firstName, String lastName, String email, String phone, String password, String username, Long id, Model model, HttpServletRequest request){
        CreateOrUpdateUserDTO dto = new CreateOrUpdateUserDTO(firstName, lastName, email, phone, password, username);
        String URL = "http://localhost:6000/user/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(URL,dto,request.getParameter("id"));
        return "redirect:/employees/all";
    }
}
