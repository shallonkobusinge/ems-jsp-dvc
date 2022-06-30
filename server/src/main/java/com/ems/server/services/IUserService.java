package com.ems.server.services;

import com.ems.server.dto.CreateOrUpdateUserDTO;
import com.ems.server.dto.LoginDTO;
import com.ems.server.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User save(CreateOrUpdateUserDTO user);
    User findById(Long id);
    User update(CreateOrUpdateUserDTO user, Long id);
    User remove(Long id);
    List<User> findAll();
    String login(LoginDTO dto);

    User findByUserNameOrEmail(String username, String email);

    User profile();
}
