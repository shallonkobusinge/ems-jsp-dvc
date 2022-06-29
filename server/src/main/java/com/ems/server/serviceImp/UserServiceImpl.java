package com.ems.server.serviceImp;

import com.ems.server.dto.CreateOrUpdateUserDTO;
import com.ems.server.dto.LoginDTO;
import com.ems.server.models.User;
import com.ems.server.repository.UserRepository;
import com.ems.server.services.IUserService;
import com.ems.server.utils.ResourceNotFoundException;
import com.ems.server.utils.Utility;
import com.ems.server.utils.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User findByUserNameOrEmail(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email).orElseThrow(()-> new ResourceNotFoundException("User with username " + username + " or email " + email + " already exists"));
    }
    @Override
    public User save(CreateOrUpdateUserDTO user) {
        User saved = new User(user);
        saved.setPassword(Utility.encodePassword(user.getPassword()));
        return userRepository.save(saved);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id.toString() + " not found"));
    }


    @Override
    public User update(CreateOrUpdateUserDTO user, Long id) {
        User existingUser = findById(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(Utility.encodePassword(user.getPassword()));
        return userRepository.save(existingUser);
    }

    @Override
    public User remove(Long id) {
        User existingUser = findById(id);
        userRepository.delete(existingUser);
        return existingUser;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String login(LoginDTO dto) {
    String jwt = null;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
        try{
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = jwtTokenProvider.generateToken(authentication);
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return jwt;
    }
}
