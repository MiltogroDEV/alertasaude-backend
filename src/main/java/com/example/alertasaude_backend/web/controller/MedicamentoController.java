package com.example.alertasaude_backend.web.controller;

import com.example.alertasaude_backend.entity.Medicamento;
import com.example.alertasaude_backend.service.MedicamentoService;
import com.example.alertasaude_backend.web.dto.MedicamentoCreateDTO;
import com.example.alertasaude_backend.web.dto.MedicamentoResponseDTO;
import com.example.alertasaude_backend.web.dto.mapper.MedicamentoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> criar(@Valid @RequestBody MedicamentoCreateDTO createDTO) {
        Medicamento user = medicamentoService.criarMedicamento(MedicamentoMapper.toMedicamento(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(MedicamentoMapper.toDTO(user));
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> listarTodos() {
        List<Medicamento> users = medicamentoService.listarTodos();
        return ResponseEntity.ok(MedicamentoMapper.toListDTO(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> buscarPorId(@PathVariable int id) {
        Medicamento user = medicamentoService.buscarPorId(id);
        return ResponseEntity.ok(MedicamentoMapper.toDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> deletarPorId(@PathVariable int id) {
        Medicamento user = medicamentoService.deletarPorId(id);
        return ResponseEntity.ok(MedicamentoMapper.toDTO(user));
    }
}
