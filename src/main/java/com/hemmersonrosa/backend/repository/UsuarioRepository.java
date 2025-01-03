package com.hemmersonrosa.backend.repository;

import com.hemmersonrosa.backend.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
