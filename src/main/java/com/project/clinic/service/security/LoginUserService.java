package com.project.clinic.service.security;


import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.login.LoginUser;
import com.project.clinic.repository.security.LoginUserRepository;
import com.project.clinic.repository.security.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserService {
  private final LoginUserRepository loginUserRepository;
  private final RolRepository rolRepository;
  private final PasswordEncoder passwordEncoder;
  
  public LoginUser upload(LoginUser user) throws BadRequestException {
    if (user == null)
      throw new BadRequestException("User can't be null");
    rolRepository.saveAll(user.getRolls());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return loginUserRepository.save(user);
  }

}
