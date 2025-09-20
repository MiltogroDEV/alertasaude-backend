package com.example.alertasaude_backend.web.controller;

import com.example.alertasaude_backend.entity.Usuario;
import com.example.alertasaude_backend.service.UsuarioService;
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
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.criarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/id")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletarPorId(@PathVariable int id) {
        usuarioService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
