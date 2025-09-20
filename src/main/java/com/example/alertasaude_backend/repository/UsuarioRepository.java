package com.example.alertasaude_backend.repository;

import com.example.alertasaude_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
