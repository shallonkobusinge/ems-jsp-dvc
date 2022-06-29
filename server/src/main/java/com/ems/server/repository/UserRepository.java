package com.ems.server.repository;

import com.ems.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndEmail(String email, String username);
    Optional<User> findByUsernameOrEmailOrPhone(String username, String email, String phone);
    Optional<User> findByEmail(String email);
}
