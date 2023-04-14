package com.project.clinic.service.security;

import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.login.LoginUser;
import com.project.clinic.login.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
  private final LoginUserService loginUserService;
  
  @Override
  public void run(ApplicationArguments args) throws BadRequestException {
    loginUserService.upload(
        LoginUser.builder()
        .username("admin")
        .email("admin@gmail.com")
        .password("admin")
        .roles(Set.of(new Role("ADMIN"), new Role("USER")))
        .build()
    );
    loginUserService.upload(
        LoginUser.builder()
            .username("user")
            .email("user@gmail.com")
            .password("user")
            .roles(Set.of(new Role("USER")))
            .build()
    );
  }
}
