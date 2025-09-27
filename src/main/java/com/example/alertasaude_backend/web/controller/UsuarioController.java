package com.example.alertasaude_backend.web.controller;

import com.example.alertasaude_backend.entity.Usuario;
import com.example.alertasaude_backend.service.UsuarioService;
import com.example.alertasaude_backend.web.dto.UsuarioCreateDTO;
import com.example.alertasaude_backend.web.dto.UsuarioGetDTO;
import com.example.alertasaude_backend.web.dto.UsuarioResponseDTO;
import com.example.alertasaude_backend.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioCreateDTO createDTO) {
        Usuario user = usuarioService.criarUsuario(UsuarioMapper.toUsuario(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDTO(user));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<Usuario> users = usuarioService.listarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDTO(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable int id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> deletarPorId(@PathVariable int id) {
        Usuario user = usuarioService.deletarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDTO(user));
    }
}
