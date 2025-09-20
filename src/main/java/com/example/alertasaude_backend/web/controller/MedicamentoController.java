package com.example.alertasaude_backend.web.controller;

import com.example.alertasaude_backend.entity.Medicamento;
import com.example.alertasaude_backend.service.MedicamentoService;
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
    public ResponseEntity<Medicamento> criar(@RequestBody Medicamento medicamento) {
        return ResponseEntity.ok(medicamentoService.criarMedicamento(medicamento));
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> listarTodos() {
        return ResponseEntity.ok(medicamentoService.listarTodos());
    }

    @GetMapping("/id")
    public ResponseEntity<Medicamento> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(medicamentoService.buscarPorId(id));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletarPorId(@PathVariable int id) {
        medicamentoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
