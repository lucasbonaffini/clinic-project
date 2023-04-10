package com.project.clinic.repository.security;

import com.project.clinic.login.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
