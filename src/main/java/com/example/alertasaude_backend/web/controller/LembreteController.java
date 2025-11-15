package com.example.alertasaude_backend.web.controller;

import com.example.alertasaude_backend.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/lembretes")
public class LembreteController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping("/enviar/{medicamentoId}")
    public ResponseEntity<Map<String, String>> enviarLembreteManual(@PathVariable Integer medicamentoId) {
        Map<String, String> response = new HashMap<>();

        try {
            agendamentoService.enviarLembreteManual(medicamentoId);
            response.put("mensagem", "Lembrete enviado com sucesso!");
            response.put("medicamentoId", medicamentoId.toString());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("erro", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/verificar-todos")
    public ResponseEntity<Map<String, String>> verificarTodosMedicamentos() {
        Map<String, String> response = new HashMap<>();

        try {
            agendamentoService.verificarMedicamentos();
            response.put("mensagem", "Verificação executada com sucesso!");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("erro", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> verificarStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ativo");
        response.put("mensagem", "Serviço de lembretes está funcionando");
        return ResponseEntity.ok(response);
    }
}
