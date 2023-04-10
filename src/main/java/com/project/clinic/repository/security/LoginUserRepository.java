package com.project.clinic.repository.security;

import com.project.clinic.login.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer> {
    Optional<LoginUser> findByUsername(String name);
}
