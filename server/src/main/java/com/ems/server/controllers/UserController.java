package com.ems.server.controllers;

import com.ems.server.dto.CreateOrUpdateUserDTO;
import com.ems.server.dto.LoginDTO;
import com.ems.server.models.User;
import com.ems.server.repository.UserRepository;
import com.ems.server.services.IUserService;
import com.ems.server.utils.APIResponse;
import com.ems.server.utils.JwtAuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;
    private final UserRepository userRepository;

    public UserController(IUserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> all(){
//        return ResponseEntity.ok(new APIResponse( HttpStatus.OK,"All users",userService.findAll()));
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody CreateOrUpdateUserDTO dto){
        Optional<User> existingUser = userRepository.findByUsernameAndEmail(dto.getUsername(),dto.getEmail());
        if(existingUser.isPresent()){
            return ResponseEntity.ok(new APIResponse(HttpStatus.OK,"User with email "+dto.getEmail()+" already exists",null));
        }
        return ResponseEntity.ok(new APIResponse( HttpStatus.OK,"User added",userService.save(dto)));
    }
    @PutMapping("/{id}")

    public ResponseEntity<?> update(@RequestBody CreateOrUpdateUserDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(new APIResponse( HttpStatus.OK,"User updated",userService.update(dto,id)));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto){
        System.out.println("From frontend "+dto.getLogin()+" "+dto.getPassword());
        return ResponseEntity.ok(userService.login(dto));
    }
    @GetMapping("/profile")
    public ResponseEntity<?> profile(){
        return ResponseEntity.ok(userService.profile());
    }
}
